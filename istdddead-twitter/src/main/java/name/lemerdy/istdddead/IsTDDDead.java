package name.lemerdy.istdddead;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class IsTDDDead {

    private final QueryResult result;

    public IsTDDDead(QueryResult result) {
        this.result = result;
    }

    public static void main(String[] args) {
        Configuration twitterAuthenticationConfiguration = new ConfigurationBuilder()
                .setOAuthConsumerKey("5eL3ACRPhze3Y5i8LBajaE721")
                .setOAuthConsumerSecret("GRC1b0aW2x4l1ehqFmaQZhPFCIAu7x5YOkESw1bbQCSrHFFDAe")
                .setOAuthAccessToken("15370457-qwr2yYCZ7WFf13JbId5LcfNID0WKwvceegrerI1Uc")
                .setOAuthAccessTokenSecret("1crMxhavzZqk3HUl2jbx1EQiiGhXFY1Lpt7ZPZP28FLLu")
                .build();
        Twitter twitter = new TwitterFactory(twitterAuthenticationConfiguration).getInstance();

        QueryResult result;
        try {
            result = twitter.search(new Query("#isTDDDead"));
        } catch (TwitterException e) {
            throw new RuntimeException();
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("results.bin"))) {
            objectOutputStream.writeObject(result);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public Map<LocalDate, Integer> tweetsByDay() {
        return result.getTweets().stream()
                .collect(groupingBy(
                        tweet -> LocalDate.from(tweet.getCreatedAt().toInstant().atZone(ZoneId.of("UTC"))),
                        summingInt(status -> 1)
                ));
    }
}
