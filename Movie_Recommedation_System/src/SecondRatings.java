/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings loader = new FirstRatings();
        myMovies = loader.loadMovies(movieFile);
        myRaters = loader.loadRaters(ratingFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minNumRater) {
        int numRater = 0;
        double totRating = 0;
        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                numRater++;
                totRating += rater.getRating(id);
            }
        }
        if (numRater >= minNumRater) {
            return totRating/numRater; 
        } else {
            return -1;
        }        
    }
    
    public ArrayList<Rating> getAverageRatings(int minNumRater) {
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            String id = movie.getID();
            double avgRating = getAverageByID(id, minNumRater);
            if (avgRating != -1) {
                Rating rating = new Rating(id, avgRating);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }
    
    public String getTitle(String id) {
        String title = "------ movie ID not found ------";
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                title = movie.getTitle();
            }
        }
        return title;        
    }
    
    public String getID(String title) {
        String id = "------ movie title not found ------";
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                id = movie.getID();
            }
        }
        return id;    
    }
}
