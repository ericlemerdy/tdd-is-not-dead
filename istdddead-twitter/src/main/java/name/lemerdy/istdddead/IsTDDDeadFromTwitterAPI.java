package name.lemerdy.istdddead;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class IsTDDDeadFromTwitterAPI implements Supplier<List<Tweet>> {

    private final List<Tweet> tweets;

    @Override
    public List<Tweet> get() {
        return tweets;
    }

    public IsTDDDeadFromTwitterAPI() {
        Configuration twitterAuthenticationConfiguration = new ConfigurationBuilder()
                .setOAuthConsumerKey("5eL3ACRPhze3Y5i8LBajaE721")
                .setOAuthConsumerSecret("GRC1b0aW2x4l1ehqFmaQZhPFCIAu7x5YOkESw1bbQCSrHFFDAe")
                .setOAuthAccessToken("15370457-qwr2yYCZ7WFf13JbId5LcfNID0WKwvceegrerI1Uc")
                .setOAuthAccessTokenSecret("1crMxhavzZqk3HUl2jbx1EQiiGhXFY1Lpt7ZPZP28FLLu")
                .build();
        Twitter twitter = new TwitterFactory(twitterAuthenticationConfiguration).getInstance();

        try {
            this.tweets = twitter.search(new Query("#isTDDDead")).getTweets().stream()
                    .map(tweet -> new Tweet(
                            Long.toString(tweet.getId()),
                            tweet.getUser().getScreenName(),
                            LocalDate.from(tweet.getCreatedAt().toInstant().atZone(ZoneId.of("UTC")))
                    ))
            .collect(toList());
        } catch (TwitterException e) {
            throw new RuntimeException();
        }

    }
}
