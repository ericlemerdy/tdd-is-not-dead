package name.lemerdy.istdddead;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import twitter4j.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class IsTDDDeadTest {

    @Mock
    private QueryResult result;
    
    private IsTDDDead isTDDDead;

    @Before
    public void createIsTDDDeadFromTwitter() {
        isTDDDead = new IsTDDDead(result);
    }

    @Test
    public void should_get_one_tweet() {
        Status tweet = tweetCreatedAt("1981-12-24T00:00:00.00Z");
        given(result.getTweets()).willReturn(asList(tweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(entry(LocalDate.of(1981, Month.DECEMBER, 24), 1));
    }

    @Test
    public void should_get_two_tweets_at_same_date() {
        Status firstTweet = tweetCreatedAt("1981-12-24T00:00:00.00Z");
        Status secondTweet = tweetCreatedAt("1981-12-24T10:00:00.00Z");
        given(result.getTweets()).willReturn(asList(firstTweet, secondTweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(entry(LocalDate.of(1981, Month.DECEMBER, 24), 2));
    }

    @Test
    public void should_get_two_tweets_at_two_dates() {
        Status firstTweet = tweetCreatedAt("1981-12-24T00:00:00.00Z");
        Status secondTweet = tweetCreatedAt("2012-09-24T00:00:00.00Z");
        given(result.getTweets()).willReturn(asList(firstTweet, secondTweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(
                entry(LocalDate.of(1981, Month.DECEMBER, 24), 1),
                entry(LocalDate.of(2012, Month.SEPTEMBER, 24), 1)
        );
    }

    @Test
    public void should_get_two_tweets_at_two_dates_order_asc() {
        Status firstTweet = tweetCreatedAt("2012-09-24T00:00:00.00Z");
        Status secondTweet = tweetCreatedAt("1981-12-24T00:00:00.00Z");
        given(result.getTweets()).willReturn(asList(firstTweet, secondTweet));

        Map<LocalDate, Integer> tweetsByDay = isTDDDead.tweetsByDay();

        assertThat(tweetsByDay).containsExactly(
                entry(LocalDate.of(1981, Month.DECEMBER, 24), 1),
                entry(LocalDate.of(2012, Month.SEPTEMBER, 24), 1)
        );
    }

    @Test
    public void should_get_empty_tweets_from_empty_file() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(getClass().getResourceAsStream("/empty.bin"))) {
            QueryResult result = (QueryResult) objectInputStream.readObject();
            Map<LocalDate, Integer> tweetsByDay = new IsTDDDead(result).tweetsByDay();
            assertThat(tweetsByDay).isEmpty();
        }
    }

    private Status tweetCreatedAt(String date) {
        Status tweet = mock(Status.class);
        given(tweet.getCreatedAt()).willReturn(Date.from(Instant.parse(date)));
        return tweet;
    }
}