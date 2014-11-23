package name.lemerdy.istdddead;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

public class IsTDDDeadTest {

    @Test
    public void should_get_one_tweet() {
        final Tweet tweet = tweetCreatedAt("1981-12-24");
        IsTDDDead isTDDDead = new IsTDDDead(() -> asList(tweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(entry(LocalDate.of(1981, Month.DECEMBER, 24), 1));
    }

    @Test
    public void should_get_two_tweets_at_same_date() {
        final Tweet firstTweet = tweetCreatedAt("1981-12-24");
        final Tweet secondTweet = tweetCreatedAt("1981-12-24");
        IsTDDDead isTDDDead = new IsTDDDead(() -> asList(firstTweet, secondTweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(entry(LocalDate.of(1981, Month.DECEMBER, 24), 2));
    }

    @Test
    public void should_get_two_tweets_at_two_dates() {
        Tweet firstTweet = tweetCreatedAt("1981-12-24");
        Tweet secondTweet = tweetCreatedAt("2012-09-24");
        IsTDDDead isTDDDead = new IsTDDDead(() -> asList(firstTweet, secondTweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(
                entry(LocalDate.of(1981, Month.DECEMBER, 24), 1),
                entry(LocalDate.of(2012, Month.SEPTEMBER, 24), 1)
        );
    }

    @Test
    public void should_get_two_tweets_at_two_dates_order_asc() {
        Tweet firstTweet = tweetCreatedAt("2012-09-24");
        Tweet secondTweet = tweetCreatedAt("1981-12-24");
        IsTDDDead isTDDDead = new IsTDDDead(() -> asList(firstTweet, secondTweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(
                entry(LocalDate.of(1981, Month.DECEMBER, 24), 1),
                entry(LocalDate.of(2012, Month.SEPTEMBER, 24), 1)
        );
    }

    private Tweet tweetCreatedAt(String date) {
        return new Tweet("", "", LocalDate.parse(date));
    }
}