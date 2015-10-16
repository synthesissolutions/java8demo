package com.java8demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dsherric on 10/13/15.
 */
public class Movie implements Comparable<Movie> {

    public enum MpaaRatings {
        G ("G"), PG ("PG"), PG13 ("PG-13"), R ("R"), X ("X");

        private final String ratingName;

        private MpaaRatings(String ratingName) {
            this.ratingName = ratingName;
        }

        @Override
        public String toString() {
            return ratingName;
        }
    };

    private String title;
    private MpaaRatings mpaaRating;
    private ArrayList<String> genres = new ArrayList<String>();
    private float rating;
    private int lengthInMinutes;
    private LocalDate releaseDate;

    public Movie(String title, MpaaRatings mpaaRating, String[] genres, float rating, int lengthInMinutes, LocalDate releaseDate) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        for (String genre : genres) {
            this.genres.add(genre);
        }
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public int compareTo(Movie m2) {
        if (this.getReleaseDate().getYear() == m2.getReleaseDate().getYear()) {
            return this.getTitle().compareTo(m2.getTitle());
        }

        return this.getReleaseDate().getYear() - m2.getReleaseDate().getYear();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MpaaRatings getMpaaRating() {
        return mpaaRating;
    }

    public void setRating(MpaaRatings rating) {
        this.mpaaRating = rating;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReleaseYear() {
        return releaseDate.getYear();
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isSciFi() {
        return genres.contains("Sci-Fi");
    }

    public boolean isGreatMovie() {
        return rating > 8.5;
    }

    public boolean isRecent() {
        return releaseDate.getYear() >= 2000;
    }

    public boolean isLong() {
        return lengthInMinutes > 120;
    }

    public String toString() {
        return releaseDate.getYear() + " - " + title;
    }

    public static List<Movie> createDemoMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        movies.add(new Movie("Star Wars: Episode IV - A New Hope", Movie.MpaaRatings.PG, new String[] {"Action", "Adventure", "Fantasy", "Sci-Fi"}, 8.7f, 121, LocalDate.of(1977, 5, 25)));
        movies.add(new Movie("Star Wars: Episode V - The Empire Strikes Back", Movie.MpaaRatings.PG, new String[] {"Action", "Adventure", "Fantasy", "Sci-Fi"}, 8.8f, 124, LocalDate.of(1980, 6, 20)));
        movies.add(new Movie("Star Wars: Episode VI - Return of the Jedi", Movie.MpaaRatings.PG, new String[] {"Action", "Adventure", "Fantasy", "Sci-Fi"}, 8.4f, 121, LocalDate.of(1983, 5, 25)));
        movies.add(new Movie("Gone with the Wind", Movie.MpaaRatings.G, new String[] {"Drama", "Romance", "War"}, 8.2f, 238, LocalDate.of(1943, 1, 23)));
        movies.add(new Movie("Casablanca", Movie.MpaaRatings.PG, new String[] {"Drama", "Romance", "War"}, 8.6f, 102, LocalDate.of(1940, 1, 17)));
        movies.add(new Movie("Twelve Monkeys", Movie.MpaaRatings.R, new String[] {"Thriller", "Sci-Fi", "Mystery"}, 8.1f, 129, LocalDate.of(1996, 1, 5)));
        movies.add(new Movie("The Martian", Movie.MpaaRatings.PG13, new String[] {"Drama", "Adventure", "Sci-Fi"}, 8.3f, 144, LocalDate.of(2015, 10, 2)));
        movies.add(new Movie("Bill & Ted's Excellent Adventure", Movie.MpaaRatings.PG, new String[] {"Adventure", "Comedy", "Sci-Fi"}, 6.9f, 90, LocalDate.of(1989, 2, 17)));
        movies.add(new Movie("Ghostbusters", Movie.MpaaRatings.PG, new String[] {"Comedy", "Fantasy"}, 7.8f, 105, LocalDate.of(1984, 6, 8)));
        movies.add(new Movie("Scream", Movie.MpaaRatings.R, new String[] {"Horror", "Mystery"}, 7.2f, 111, LocalDate.of(1996, 12, 20)));
        movies.add(new Movie("Independence Day", MpaaRatings.PG13, new String[] {"Action", "Adventure", "Sci-Fi"}, 6.9f, 145, LocalDate.of(1996, 7, 3)));

        return movies;
    }

    public static void printMovieList(String title, List<Movie> movies) {
        System.out.println(title);
        movies.stream().forEach(m -> System.out.println(m));
        System.out.println();
    }
}
