package name.lemerdy.istdddead;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.Long.parseLong;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.unmodifiableList;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

public class IsTDDDeadFromBrowser {
    final List<Tweet> tweets;

    public IsTDDDeadFromBrowser(String firstSearch, String... infiniteLoad) {
        List<Tweet> tweets = getTweetsFromFirstSearch(new File(uri(firstSearch)).toPath());
        tweets.addAll(getTweetsFromInfiniteLoad(infiniteLoad));
        
        this.tweets = unmodifiableList(tweets);
    }

    private List<Tweet> getTweetsFromFirstSearch(Path firstSearch) {
        return extractTweetsFrom(lines(firstSearch));
    }

    private List<Tweet> getTweetsFromInfiniteLoad(String... infiniteLoad) {
        List<Tweet> tweets = new ArrayList<>();
        for (String load : infiniteLoad) {
            try (final BufferedReader in = Files.newBufferedReader(new File(uri(load)).toPath())) {
                tweets.addAll(
                        extractTweetsFrom(linesSplittedByAntislashThenN(in)
                                .map(line -> line.replaceAll("\\\\\"", "\"").replaceAll("\\\\/", "/")))
                );
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return tweets;
    }

    private List<Tweet> extractTweetsFrom(Stream<String> lines) {
        Pattern pattern = Pattern.compile("href=\"/([^/]+)/status/(\\d+)\".+data-time=\"(\\d+)\"");
        return lines.filter(line -> line.contains("tweet-timestamp"))
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> new Tweet(matcher.group(2), matcher.group(1), Instant.ofEpochSecond(parseLong(matcher.group(3))).atOffset(UTC).toLocalDate()))
                .collect(toList());
    }

    private Stream<String> lines(Path firstSearch) {
        try {
            return Files.lines(firstSearch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<String> linesSplittedByAntislashThenN(final BufferedReader in) {
        Iterator<String> iter = new Iterator<String>() {
            Optional<String> nextLine = empty();

            @Override
            public boolean hasNext() {
                if (flush()) {
                    return true;
                } else {
                    try {
                        Optional<Character> previousCharacter = empty();
                        StringBuilder line = new StringBuilder();
                        int c;
                        while ((c = in.read()) != -1) {
                            if (previousCharacter.orElse((char) -1) == '\\' && c == 'n') {
                                nextLine = Optional.of(line.toString());
                                break;
                            }
                            previousCharacter.ifPresent(line::append);
                            previousCharacter = Optional.of((char) c);
                        }
                        return flush();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
            }

            @Override
            public String next() {
                if (flush() || hasNext()) {
                    String line = nextLine.get();
                    nextLine = empty();
                    return line;
                } else {
                    throw new NoSuchElementException();
                }
            }

            private boolean flush() {
                return nextLine.isPresent();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    private URI uri(String resourceName) {
        try {
            return getClass().getResource(resourceName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
