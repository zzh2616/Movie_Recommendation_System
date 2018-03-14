import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRating {
    public ArrayList<Movie> loadMovies(String fname) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        String path = "./data/" + fname;
        FileResource fr = new FileResource(path);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            Movie newMovie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            movieList.add(newMovie);
        }
        return movieList;
    }

    public ArrayList<Rater> loadRaters(String fname) {
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        // add another rater id list to judge whether the rater list contains the rater
        ArrayList<String> raterIDList = new ArrayList<String>();
        String path = "./data/" + fname;
        FileResource fr = new FileResource(path);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            Rater newRater = new Rater(record.get("rater_id"));
            if (raterIDList.contains(record.get("rater_id"))) {
                raterList.get(raterIDList.indexOf(record.get("rater_id"))).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            } else {
                newRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raterList.add(newRater);
                raterIDList.add(record.get("rater_id"));
            }
        }
        return raterList;
    }

    public static void main(String[] args) {
        FirstRating test = new FirstRating();
        // test loadMovies method
/*        ArrayList<Movie> movieList = test.loadMovies("ratedmoviesfull.csv");
        // System.out.println(movieList);
        System.out.println("movie list size: " + movieList.size());
        int genreCount = 0;
        for (Movie movie : movieList) {
            if (movie.getGenres().contains("Comedy")) {
                genreCount++;
            }
        }
        System.out.println("number of comedys: " + genreCount);
        int longMovieCount = 0;
        for (Movie movie : movieList) {
            if (movie.getMinutes() > 150) {
                longMovieCount++;
            }
        }
        System.out.println("number of movies longer than 150 minutes: " + longMovieCount);
        HashMap<String, ArrayList<Movie>> directorMovieMap = new HashMap<String, ArrayList<Movie>>();
        for (Movie movie : movieList) {
            String[] directors = movie.getDirector().split("\\s*,\\s*");
            for (int i = 0; i < directors.length; i++) {
                String director = directors[i];
                if (!directorMovieMap.containsKey(director)) {
                    ArrayList<Movie> directorMovieList = new ArrayList<Movie>();
                    directorMovieList.add(movie);
                    directorMovieMap.put(director, directorMovieList);
                } else {
                    ArrayList<Movie> directorMovieList = directorMovieMap.get(director);
                    directorMovieList.add(movie);
                    directorMovieMap.put(director, directorMovieList);
                }
            }
        }
        int maxNumMoviesPerDirector = 0;
        for (ArrayList<Movie> directorMovieList : directorMovieMap.values()) {
            if (directorMovieList.size() > maxNumMoviesPerDirector) {
                maxNumMoviesPerDirector = directorMovieList.size();
            }
        }
        System.out.println("maximum number of movies by any director is: " + maxNumMoviesPerDirector);
        ArrayList<String> directorList = new ArrayList<String>();
        for (String director : directorMovieMap.keySet()) {
            if (directorMovieMap.get(director).size() == maxNumMoviesPerDirector) {
                directorList.add(director);
            }
        }
        System.out.println("number of high output directors is: " + directorList.size());
        System.out.println("high output directors are " + directorList);*/
        // test loadRaters method
        ArrayList<Rater> raterList = test.loadRaters("ratings.csv");
        System.out.println("number of raters: " + raterList.size());
/*        for (Rater rater : raterList) {
            System.out.println("rater id: " + rater.getID() + " number of rating: " + rater.numRatings());
            for (String ratedItem : rater.getItemsRated()) {
                System.out.println(ratedItem + ": " + rater.getRating(ratedItem));
            }
        }*/
        String raterID = "193";
        for (Rater rater : raterList) {
            if (rater.getID().equals(raterID)) {
                System.out.println("rater id: " + raterID + " number of rating: " + rater.numRatings());
            }
        }
        int maxNumRatingsPerRater = 0;
        for (Rater rater : raterList) {
            if (rater.numRatings() > maxNumRatingsPerRater) {
                maxNumRatingsPerRater = rater.numRatings();
            }
        }
        System.out.println("maximum number of ratings by any rater is " + maxNumRatingsPerRater);
        ArrayList<String> activeRaterList = new ArrayList<String>(); 
        for (Rater rater : raterList) {
            if (rater.numRatings() == maxNumRatingsPerRater) {
                activeRaterList.add(rater.getID());
            }
        }
        System.out.println("number of high output rater is: " + activeRaterList.size());
        System.out.println("high output rater are " + activeRaterList);
        String movieID = "1798709";
        int numMovieRated = 0;
        for (Rater rater : raterList) {
            if (rater.hasRating(movieID)) {
                numMovieRated++;
            }
        }
        System.out.println("movie id: " + movieID + " times rated: " + numMovieRated);
        ArrayList<String> ratedMovieList = new ArrayList<String>();
        for (Rater rater : raterList) {
            ArrayList<String> ratedMovies = rater.getItemsRated();
            for (String movie : ratedMovies) {
                if (!ratedMovieList.contains(movie)) {
                    ratedMovieList.add(movie);
                }
            }
        }
        System.out.println("number of rated movies: " + ratedMovieList.size());
    }
}
