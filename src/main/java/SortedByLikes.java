import java.util.Comparator;
import java.util.Date;

public class SortedByLikes implements Comparator<Tweet> {
    public int compare(Tweet obj1, Tweet obj2) {
        int likes1 = obj1.getFavoriteCount();
        int likes2 = obj2.getFavoriteCount();

        if(likes1 > likes2) {
            return 1;
        }
        else if(likes1 < likes2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

