package name.lemerdy.istdddead;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;
import static java.time.Month.JULY;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

public class IsTDDDeadFromBrowserTest {
    @Test
    public void should_get_tweets_from_web_page() {
        IsTDDDeadFromBrowser isTDDDeadFromBrowser = new IsTDDDeadFromBrowser("/acceptance/search.html");

        List<Tweet> tweets = isTDDDeadFromBrowser.get();

        assertThat(tweets).hasSize(20)
                .startsWith(
                        new Tweet("523099894132006912", "danielbaleato", LocalDate.of(2014, OCTOBER, 17)),
                        new Tweet("519910898195238912", "agilequote", LocalDate.of(2014, OCTOBER, 8))
                );
    }

    @Test
    public void should_get_tweets_from_infinite_load() {
        IsTDDDeadFromBrowser isTDDDeadFromBrowser = new IsTDDDeadFromBrowser("/acceptance/search.html", "/acceptance/timeline.json");

        List<Tweet> tweets = isTDDDeadFromBrowser.get();

        assertThat(tweets)
                .hasSize(40)
                .containsSequence(
                        new Tweet("495167160453709824", "zazoomauro", LocalDate.of(2014, AUGUST, 1)),
                        new Tweet("493671272786968576", "Allh0p3Isl0st", LocalDate.of(2014, JULY, 28))
                );
    }
}