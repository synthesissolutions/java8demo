package com.java8demo;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;

/**
 * Created by dsherric on 10/13/15.
 */
public class SortMain {
    public static void main(String[] args) {
        List<Movie> movies = Movie.createDemoMovies();

        // Sort by year and title ascending
        Collections.sort(movies);
        Movie.printMovieList("** Sorted List **", movies);

        Comparator<Movie> movieTitleComparator = (Movie m1, Movie m2) -> m1.getTitle().compareTo(m2.getTitle());
        Comparator<Movie> movieYearComparator = (Movie m1, Movie m2) -> Integer.compare(m1.getReleaseYear(), m2.getReleaseYear());
        Comparator<Movie> movieYearAndTitleComparator = (Movie m1, Movie m2) -> movieYearComparator.thenComparing(movieTitleComparator).compare(m1, m2);

        Collections.sort(movies, movieTitleComparator);
        Movie.printMovieList("** Sorted List By Title **", movies);

        Collections.sort(movies, movieYearAndTitleComparator);
        Movie.printMovieList("** Sorted List By Year and Title **", movies);
    }
}