/**
 * Created by anton on 19.05.16.
 */

import java.util.*;

public class TweetsContainerImpl<T extends Tweet> implements TweetsContainer<T>  {

    private Set<T> tweets = new TreeSet<T>();

    public int size() {
        return tweets.size();
    }

    public boolean add(T tweet) {
        return tweets.add(tweet);
    }

    public boolean addAll(Collection<? extends T> tweetsSet) {
        return tweets.addAll(tweetsSet);
    }

    public boolean remove(T tweet) {
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
        return null;
    }

    public Map<String, Double> getTagCloud(String lang) {
        return null;
    }

    public Iterator<T> iterator() {
        return tweets.iterator();
    }
}
