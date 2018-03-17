import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingFile) {
        FirstRatings loader = new FirstRatings();
        myRaters = loader.loadRaters(ratingFile);
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
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        for (String movie : myMovies) {
            double avgRating = getAverageByID(movie, minNumRater);
            if (avgRating != -1) {
                Rating rating = new Rating(movie, avgRating);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }
   
    public ArrayList<Rating> getAverageRatingsByFilter(int minNumRater, Filter f) {
        ArrayList<String> myMovies = MovieDatabase.filterBy(f);
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        for (String movie : myMovies) {
            double avgRating = getAverageByID(movie, minNumRater);
            if (avgRating != -1) {
                Rating rating = new Rating(movie, avgRating);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }
}