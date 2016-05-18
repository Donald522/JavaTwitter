import java.util.Comparator;
import java.util.Date;

public class SortedByDate implements Comparator<Tweet> {
    public int compare(Tweet obj1, Tweet obj2) {
        Date date1 = obj1.getDate();
        Date date2 = obj2.getDate();

        return date1.compareTo(date2);
    }
}

