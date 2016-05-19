/**
 * Created by anton on 19.05.16.
 */

import java.util.*;

public class TweetsContainerImpl<T extends Tweet> implements TweetsContainer<T>  {

    private List<T> tweets = new ArrayList<T>();

    public int size() {
        return tweets.size();
    }

    public boolean add(T tweet) {
        return tweets.add(tweet);
    }

    public boolean addAll(Collection<T> tweetsSet) {
        return tweets.addAll(tweetsSet);
    }

    public boolean remove(T tweet) {
        return tweets.remove(tweet);
    }

    public void clear() {
        tweets.clear();
    }

    public Tweet getOldest() {
        T oldest = tweets.get(0);
        for (T tweet : tweets) {
            if(tweet.getDate().before(oldest.getDate())) {
                oldest = tweet;
            }
        }
        return oldest;
    }

    public Tweet getTopRated() {
        T maxRated = tweets.get(0);
        for(T tweet : tweets) {
            if(tweet.getFavoriteCount() > maxRated.getFavoriteCount()) {
                maxRated = tweet;
            }
        }
        return maxRated;
    }

    public void sort(Comparator<T> comparator) {
        Collections.sort(tweets, comparator);
    }

    public Map<String, Collection<T>> groupByLang() {
        return null;
    }

    public Map<String, Double> getTagCloud(String lang) {
        return null;
    }

    public Iterator<T> iterator() {
        return tweets.iterator();
    }
}
