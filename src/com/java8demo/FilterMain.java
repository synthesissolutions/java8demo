package com.java8demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterMain {

    public static void main(String[] args) {
	    List<Movie> movies = Movie.createDemoMovies();

        // Kicking it old-school
        List<Movie> filteredMovies = getMoviesByGenre(movies, "Sci-Fi");
        Movie.printMovieList("** Sci-Fi **", filteredMovies);

        List<Movie> greatMovies = getGreatMovies(movies);
        Movie.printMovieList("** Great Movies **", greatMovies);

        List<Movie> longMovies = filterMoviesPredicate(movies, Movie::isLong);
        Movie.printMovieList("** Long Movies **", longMovies);

        // Anonymous Inner Class
        List<Movie> longGreatMovies = filterMoviesByMoviePredicate(movies, new MoviePredicate() {
            @Override
            public boolean test(Movie m) {
                return m.isGreatMovie() && m.isLong();
            }
        });
        Movie.printMovieList("** Long Great Movies **", longGreatMovies);

        // Now for the Java 8 way (minus streams which we will talk about later)
        //List<Movie> greatSciFiMovies =  filterMoviesPredicate(movies, m -> m.isGreatMovie() && m.isSciFi());
        List<Movie> greatSciFiMovies =  filterMoviesPredicate(movies, m -> m.getRating() > 8.5f && m.getGenres().contains("Sci-Fi"));
        Movie.printMovieList("** Great Sci-Fi Movies **", greatSciFiMovies);

        Predicate<Movie> greatDramasFilter = m -> m.getRating() > 8.5f && m.getGenres().contains("Drama");
        List<Movie> greatActionMovies =  filter(movies, greatDramasFilter);
        Movie.printMovieList("** Great Dramas **", greatActionMovies);

        // Preview of streams
        List<Movie> greatRecentMovies = movies.stream().filter(m -> m.isRecent() && m.isGreatMovie()).collect(Collectors.toList());
        Movie.printMovieList("** Great Recent Movies **", greatRecentMovies);
    }

    public static List<Movie> getMoviesByGenre(List<Movie> movies, String genre) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenres().contains(genre)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    public static List<Movie> getGreatMovies(List<Movie> movies) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.isGreatMovie()) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    public static List<Movie> filterMoviesByMoviePredicate(List<Movie> movies, MoviePredicate mp) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (mp.test(movie)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    public static List<Movie> filterMoviesPredicate(List<Movie> movies, Predicate<Movie> p) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (p.test(movie)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> filteredList = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    interface MoviePredicate {
        public boolean test(Movie m);
    }
}
