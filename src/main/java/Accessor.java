/**
 * Created by anton on 15.05.16.
 */

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Collection;

public class Accessor {

    private static Twitter log() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("PTNcJUZmk5w75LEQsSraCuKo1")
                .setOAuthConsumerSecret("1NoB3ANMKEDrlabb5C1b3oBdminESRsTrgfKR6dAjhOHPx4oyo")
                .setOAuthAccessToken("703693954907299840-hhW6FX4nmKnECqsLT4CP7v9EG6rE8FA")
                .setOAuthAccessTokenSecret("sIQ9JTCnCxfUvBd2pwFvpTbERExLHkmJPGcch4hiPv7Km");

        return new TwitterFactory(cb.build()).getInstance();
    }

    public static Collection<Tweet> search(String pattern, String date, int querySize) {

        Twitter twitter = log();

        int length = 100;
        long lastID = Long.MAX_VALUE;
        Query query = new Query(pattern);
        query.setSince(date);
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        while (tweets.size() < querySize) {
            if (querySize - tweets.size() > length)
                query.setCount(length);
            else
                query.setCount(querySize - tweets.size());
            try {
                QueryResult result = twitter.search(query);
                for(Status s : result.getTweets()) {
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
            }
            query.setMaxId(lastID - 1);
        }
        return tweets;
    }
}
