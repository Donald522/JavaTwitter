/**
 * Created by anton on 19.05.16.
 */

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class TweetsContainerTest<T extends Tweet> {

    private TweetsContainer<T> tweets;
    @Before
    public void setUp() throws Exception {
        tweets = new TweetsContainerImpl<T>();
    }
    @Test()
    public void createTest() throws Exception {
        assertNotNull(tweets);
        assertEquals(0, tweets.size());
    }
    @Test()
    public void methodWithOneTweetTest() throws Exception {
        Tweet tweet = new Tweet("Real is the best", new Date(1111111111), 10, 20, "en");
        assertEquals(false, tweets.remove((T) tweet));
        assertTrue(tweets.add((T) tweet));
        assertEquals(1, tweets.size());
        assertEquals(10, tweets.getTopRated().getFavoriteCount());
        assertEquals(new Date(1111111111), tweets.getOldest().getDate());
        assertTrue(tweets.remove((T) tweet));
        assertEquals(0, tweets.size());
    }
    @Test()
    public void methodWithTwoTweetsTest() throws Exception {
        Tweet tweet = new Tweet("Real is the best", new Date(1111111111), 10, 20, "en");
        Tweet tweet1 = new Tweet("Atletico is the best", new Date(222222222), 15, 25, "en");
        Collection<T> tweetsSet = new ArrayList<T>();
        tweetsSet.add((T) tweet);
        tweetsSet.add((T) tweet1);

        assertTrue(tweets.addAll(tweetsSet));
        assertEquals(2, tweets.size());
        assertEquals(15, tweets.getTopRated().getFavoriteCount());
        assertEquals(new Date(222222222), tweets.getOldest().getDate());
        tweets.sort((Comparator<T>) new SortedByDate());
        SortedSet<T> sortedTweets = new TreeSet<T>(new SortedByDate());
        sortedTweets.add((T) tweet);
        sortedTweets.add((T) tweet1);
        Iterator<T> it1 = tweets.iterator();
        Iterator<T> it2 = sortedTweets.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            assertEquals(it1.next(), it2.next());
        }
        Map<String, Collection<T>> res = tweets.groupByLang();
        assertEquals(1, res.size());
        assertEquals(2, res.get("en").size());

        tweets.clear();
        assertEquals(0, tweets.size());
    }
    @Test()
    public void tagCloudTest() throws Exception {
        Tweet tweet = new Tweet("Real is the best", new Date(1111111111), 10, 20, "en");
        Tweet tweet1 = new Tweet("Atletico is the best", new Date(222222222), 15, 25, "en");
        tweets.add((T) tweet);
        tweets.add((T) tweet1);

        Map<String, Double> cloud = tweets.getTagCloud("en");
        double sum = 0;
        for(Double val : cloud.values()) {
            sum += val;
        }
        assertEquals((double) 1, sum, 1e-6);
    }
}
