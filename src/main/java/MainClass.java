import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by anton on 16.05.16.
 */
public class MainClass {
    public static void main(String[] args) {
        System.out.println("start\n");
        Collection<Tweet> tweetsCollection = Accessor.search("Real Madrid", "2016-04-18", 10);
        TweetsContainer<Tweet> tweets = new TweetsContainerImpl<Tweet>();
        tweets.addAll(tweetsCollection);
        System.out.println("number of tweets = " + tweets.size());

        System.out.println("=======Without sort========");
        for(Tweet t : tweets) {
            System.out.println(t);
            System.out.println("--------------------");
        }

        System.out.println("=======Sort by Retweets========");
        tweets.sort(new SortedByRetweets());
        for(Tweet t : tweets) {
            System.out.println(t);
            System.out.println("--------------------");
        }
        System.out.println("=======Top Rated=======");
        System.out.println(tweets.getTopRated());

        System.out.println("========Group by lang=======");
        System.out.println(tweets.groupByLang());

        tweets.getTagCloud("en");
    }
}
