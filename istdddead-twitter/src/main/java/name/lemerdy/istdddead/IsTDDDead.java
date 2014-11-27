package name.lemerdy.istdddead;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class IsTDDDead {
    private final List<Tweet> tweets;

    public IsTDDDead(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Map<LocalDate, Long> tweetsByDay() {
        return tweets.stream().collect(groupingBy(tweet -> tweet.date, counting()));
    }
}
