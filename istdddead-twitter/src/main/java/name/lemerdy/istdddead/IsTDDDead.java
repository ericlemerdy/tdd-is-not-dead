package name.lemerdy.istdddead;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class IsTDDDead {

    private final Supplier<List<Tweet>> tweets;

    public IsTDDDead(Supplier<List<Tweet>> tweets) {
        this.tweets = tweets;
    }

    public Map<LocalDate, Integer> tweetsByDay() {
        return tweets.get().stream().collect(groupingBy(tweet -> tweet.date, summingInt(tweet -> 1)));
    }
}
