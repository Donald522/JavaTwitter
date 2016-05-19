/**
 * Created by anton on 17.05.16.
 */
//package ???.twitteraccessor;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable, Comparable {
    private String content;
    private Date timestamp;
    private int favoriteCount;
    private int retweetCount;
    private String lang;

    public Tweet(String cont, Date date, int likes, int retw, String l) {
        content = cont;
        timestamp = date;
        favoriteCount = likes;
        retweetCount = retw;
        lang = l;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(getClass() == obj.getClass())) {
            return false;
        }
        else {
            Tweet tmp = (Tweet) obj;
            if ((tmp.content.equals(this.content)) && (tmp.timestamp == this.timestamp)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {

        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + favoriteCount * retweetCount + content.length();
        return result;

    }

    @Override
    public String toString() {
        return content + "\n" + timestamp + "\n" + favoriteCount + "\n" + retweetCount + "\n" + lang;
    }

    public int compareTo(Object object) {
        if (object == null) {
            throw new NullPointerException("Null parameter");
        }
        else if (!this.getClass().equals(object.getClass())) {
            throw new ClassCastException("Possible ClassLoader issue.");
        }
        else {
            Tweet tmp = (Tweet) object;
            return this.content.compareTo(tmp.content);
        }
    }
    public Date getDate() {
        return timestamp;
    }
    public int getFavoriteCount() {
        return favoriteCount;
    }
    public int getRetweetCount() {
        return retweetCount;
    }
    public String getLang() {return lang;}
    public String getContent() {return content;}
}

