/**
 * Created by anton on 15.05.16.
 */

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Accessor {

    private static Twitter log() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("***")
                .setOAuthConsumerSecret("***")
                .setOAuthAccessToken("***")
                .setOAuthAccessTokenSecret("***");

        return new TwitterFactory(cb.build()).getInstance();
    }

    public static Collection<Tweet> search(String pattern, String date, int querySize) {

        Twitter twitter = log();

        final int length = 100;
        long lastID = Long.MAX_VALUE;
        Query query = new Query(pattern);
        query.setSince(date);
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        while (tweets.size() < querySize) {
            if (querySize - tweets.size() > length) {
                query.setCount(length);
            }
            else {
                query.setCount(querySize - tweets.size());
            }
            try {
                QueryResult result = twitter.search(query);
                for (Status s : result.getTweets()) {
                    if (s.getId() < lastID) lastID = s.getId();
                    Tweet tweet = new Tweet(
                            s.getText(),
                            s.getCreatedAt(),
                            s.getFavoriteCount(),
                            s.getRetweetCount(),
                            s.getLang()
                    );
                    tweets.add(tweet);
                }
            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
                try {
                    System.out.println("Sleeping...");
                    TimeUnit.MINUTES.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            query.setMaxId(lastID - 1);
        }
        return tweets;
    }
}
