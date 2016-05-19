/**
 * Created by anton on 19.05.16.
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TweetsContainerTest<T extends Tweet> {

    private TweetsContainer<T> tweets;
    @Before
    public void setUp() throws Exception {
        tweets = new TweetsContainerImpl<T>();
    }
    @Test()
    public void testTest() throws Exception {
        assertNotNull(tweets);
        assertNull(tweets.getOldest());
        assertNull(tweets.getTopRated());
    }
}
