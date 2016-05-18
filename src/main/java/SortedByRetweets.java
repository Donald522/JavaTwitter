import java.util.Comparator;

public class SortedByRetweets implements Comparator<Tweet> {
    public int compare(Tweet obj1, Tweet obj2) {
        int retweets1 = obj1.getRetweetCount();
        int retweets2 = obj2.getRetweetCount();

        if(retweets1 > retweets2) {
            return 1;
        }
        else if(retweets1 < retweets2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
