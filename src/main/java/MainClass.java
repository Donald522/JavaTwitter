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
        Collection<Tweet> tweetsCollection = Accessor.search("Real Madrid", "2016-04-18", 5);
        List<Tweet> tweets = new ArrayList<Tweet>(tweetsCollection);

        System.out.println("number of tweets = " + tweets.size());

        System.out.println("=======Without sort========");
        for(Tweet t : tweets) {
            System.out.println(t);
            System.out.println("--------------------");
        }

        System.out.println("=======Sort by Date========");
        Collections.sort(tweets, new SortedByDate());
        for(Tweet t : tweets) {
            System.out.println(t);
            System.out.println("--------------------");
        }

        System.out.println("=======Sort by Likes========");
        Collections.sort(tweets, new SortedByLikes());
        for(Tweet t : tweets) {
            System.out.println(t);
            System.out.println("--------------------");
        }

        System.out.println("=======Sort by Retweets========");
        Collections.sort(tweets, new SortedByRetweets());
        for(Tweet t : tweets) {
            System.out.println(t);
            System.out.println("--------------------");
        }
    }
}
