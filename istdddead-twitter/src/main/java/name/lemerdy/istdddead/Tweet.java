package name.lemerdy.istdddead;

import java.time.LocalDate;

public class Tweet {
    private final String id;
    private final String author;
    private final LocalDate date;

    public Tweet(String id, String author, LocalDate date) {
        this.id = id;
        this.author = author;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        return date.equals(tweet.date) && (id + author).equals(tweet.id + tweet.author);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
