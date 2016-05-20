/**
 * Created by anton on 19.05.16.
 */

import java.util.*;
import java.util.regex.*;

public class TweetsContainerImpl<T extends Tweet> implements TweetsContainer<T>  {

    @Override
    public Iterator<T> iterator() {
        return tweets.iterator();
    }

    private Set<T> tweets = new TreeSet<T>();

    public int size() {
        return tweets.size();
    }

    public boolean add(T tweet) {
        if(tweet == null) {
            return false;
        }
        return tweets.add(tweet);
    }

    public boolean addAll(Collection<? extends T> tweetsSet) {
        if(tweetsSet == null) {
            return false;
        }
        return tweets.addAll(tweetsSet);
    }

    public boolean remove(T tweet) {
        if(tweet == null) {
            return false;
        }
        return tweets.remove(tweet);
    }

    public void clear() {
        tweets.clear();
    }

    public Tweet getOldest() {
        return Collections.min(tweets, new SortedByDate());
    }

    public Tweet getTopRated() {
        return Collections.max(tweets, new SortedByLikes());
    }

    public void sort(Comparator<T> comparator) {
        SortedSet<T> tmp = new TreeSet<T>(comparator);
        tmp.addAll(tweets);
        tweets = tmp;
    }

    public Map<String, Collection<T>> groupByLang() {
        Map<String, Collection<T>> grouped = new HashMap<String, Collection<T>>();
        for(T tweet : tweets) {
            String curLang = tweet.getLang();
            if(!grouped.containsKey(curLang)) {
                grouped.put(curLang, new ArrayList<T>());
            }
            grouped.get(curLang).add(tweet);
        }
        return grouped;
    }

    public Map<String, Double> getTagCloud(String lang) {
        if(lang == null) {
            return null;
        }
        Map<String, Double> tagCloud = new HashMap<String, Double>();
        Collection<String> words;
        int numberOfWords = 0;
        for(T tweet : tweets) {
            if(tweet.getLang().compareTo(lang) == 0) {
                words = this.getWords(tweet.getContent());
                if(words == null) {
                    return null;
                }
                for (String word : words) {
                    if(!tagCloud.containsKey(word)) {
                        tagCloud.put(word, (double) 0);
                    }
                    tagCloud.put(word, tagCloud.get(word) + 1);
                }
                numberOfWords += words.size();
            }
        }
        for (String word : tagCloud.keySet()) {
            tagCloud.put(word, tagCloud.get(word) / numberOfWords);
        }
        return tagCloud;
    }

    private Collection<String> getWords(String tweet) {
        if(tweet == null) {
            return null;
        }
        final int minLength = 3;
        List<String> words = new ArrayList<String>();
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(tweet);
        while (m.find()) {
            if(tweet.substring(m.start(), m.end()).length() > minLength) {
                words.add(tweet.substring(m.start(), m.end()));
            }
        }
        return words;
    }
}
