package com.java8demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by dsherric on 10/14/15.
 */
public class StreamsMain {
    public static void main(String[] args) {
        List<Movie> movies = Movie.createDemoMovies();

        Comparator<Movie> movieTitleComparator = (Movie m1, Movie m2) -> m1.getTitle().compareTo(m2.getTitle());
        Comparator<Movie> movieYearComparator = (Movie m1, Movie m2) -> Integer.compare(m1.getReleaseYear(), m2.getReleaseYear());
        Comparator<Movie> movieYearAndTitleComparator = (Movie m1, Movie m2) -> movieYearComparator.thenComparing(movieTitleComparator).compare(m1, m2);

        List<Movie> test = movies.stream().filter(Movie::isGreatMovie).collect(Collectors.toList());
        Movie.printMovieList("** Great Movies **", test);

        List<Movie> test2 = movies.stream().sorted(movieYearComparator).collect(Collectors.toList());
        Movie.printMovieList("** Sorted Movies **", test2);

        double averageYear = movies.stream().collect(Collectors.averagingInt(Movie::getReleaseYear));
        System.out.println("Average Year: " + averageYear);

        double averageRating = movies.stream()
                .filter(Movie::isSciFi)
                .collect(Collectors.averagingDouble(Movie::getRating));
        System.out.println("Average: " + averageRating);

        OptionalInt max = movies.stream().mapToInt(Movie::getReleaseYear).max();
        System.out.println("Max Year: " + max);

        System.out.println("\n** Title Length > 10 **");
        movies.stream()
                .map(m -> m.getTitle())
                .filter(s -> s.length() > 10)
                .forEach(System.out::println);

        System.out.println("\n** Title starts with S **");
        movies.stream()
                .map(m -> m.getTitle())
                .filter(s -> s.startsWith("S"))
                .forEach(System.out::println);

        System.out.println("\n** Titles joined in a list **");
        String joined = movies.stream()
                .map(m -> m.getTitle())
                .map(t -> t.toLowerCase())
                .collect(Collectors.joining(", "));
        System.out.println(joined);

        System.out.println("\n** Unique Genres Sorted **");
        String uniqueGenres = movies.stream()
                .flatMap(m -> m.getGenres()
                        .stream()).distinct()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(uniqueGenres);


        try {
            Stream<String> lines = Files.lines(Paths.get(".", "zip_code_database.csv"));

            List<ZipCode> zipCodes = lines.skip(1).map(line -> ZipCode.createFromCSV(line)).collect(Collectors.toList());

            System.out.println("Count: " + zipCodes.size());

            LocalDateTime startTime = LocalDateTime.now();

            // Attempt to show how parallel streams can be faster, be this fails
            // The actual work being done is not "complex" enough
            List<String> citiesWithJ = zipCodes.parallelStream()
                    .filter(z -> z.getPrimaryCity().toLowerCase().contains("a"))
                    .map(z -> z.getPrimaryCity())
                    .map(c -> c.toUpperCase())
                    .map(c -> c + c.length())
                    .map(c -> c.toLowerCase())
                    .map(c -> c.replace('a', 'A'))
                    .map(c -> c + " ")
                    .map(c -> c + c.indexOf('a'))
                    .map(c -> c.substring(0, c.length() - 2))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            LocalDateTime endTime = LocalDateTime.now();

            Duration elapsedTime = Duration.between(startTime, endTime);
            System.out.println("Elapsed Time: " + elapsedTime);
            System.out.println("Count: " + citiesWithJ.size());

            lines.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        long maxRange = 100_000_000;
        long start = System.nanoTime();
        long answer = LongStream.rangeClosed(1, maxRange).reduce(0L, Long::sum);
        long end = System.nanoTime();

        System.out.println("Sequential Stream in ms: " + (end - start) / 1_000_000);

        start = System.nanoTime();
        answer = LongStream.rangeClosed(1, maxRange).parallel().reduce(0L, Long::sum);
        end = System.nanoTime();

        System.out.println("Parallel Stream in ms: " + (end - start) / 1_000_000);

    }
}
