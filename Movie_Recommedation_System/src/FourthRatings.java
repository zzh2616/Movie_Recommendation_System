import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
    private double getAverageByID(String id, int minNumRater) {
        int numRater = 0;
        double totRating = 0;
        for (Rater rater : RaterDatabase.getRaters()) {
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
    
    private int dotProduct(Rater me, Rater r) {
        int ans = 0;
        ArrayList<String> meRated = me.getItemsRated();
        for (String item : meRated) {
            if (me.hasRating(item) && r.hasRating(item)) {
                ans += (me.getRating(item) - 5) * (r.getRating(item) - 5);
            }
        }
        return ans;
    }
  
//    calculate a list of similarity scores of a rater to other raters in the database
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> simiList = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(me.getID())) {
                double simiScore = dotProduct(me, r);
                if (simiScore > 0) {
                    Rating simiRating = new Rating(r.getID(), simiScore);
                    simiList.add(simiRating);  
                }
            }
        }
        Collections.sort(simiList,Collections.reverseOrder());
        return simiList;
    }
    
//    generate a list of weighted averaged movie scores based on top numSimilarRaters number of raters in the user's similarity score list, the minimum number of raters used in the calculation must be no smaller than minNumrater
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minNumRater) {
        ArrayList<Rating> simiRatingList = new ArrayList<Rating>();
        ArrayList<Rating> simiList = getSimilarities(id);
//        in case that the similarity score list has a smaller size than the numSimilarRaters
        if (simiList.size() < numSimilarRaters) {
            numSimilarRaters = simiList.size();
        }
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for (String movie : myMovies) {
            double totRating = 0;
//            double totWeight = 0;
            int numRater = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                String raterID = simiList.get(i).getItem();
                double weight = simiList.get(i).getValue(); 
                if (RaterDatabase.getRater(raterID).hasRating(movie)) {
                    numRater++;
                    totRating += weight*RaterDatabase.getRater(raterID).getRating(movie);
//                    totWeight += weight;
                }
            }
            if (numRater >= minNumRater) {
                simiRatingList.add(new Rating(movie, totRating/numRater));
            }
        }
        Collections.sort(simiRatingList,Collections.reverseOrder());
        return simiRatingList;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minNumRater, Filter f) {
        ArrayList<Rating> simiRatingList = new ArrayList<Rating>();
        ArrayList<Rating> simiList = getSimilarities(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(f);
        for (String movie : myMovies) {
            double totRating = 0;
//            double totWeight = 0;
            int numRater = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                String raterID = simiList.get(i).getItem();
                double weight = simiList.get(i).getValue(); 
                if (RaterDatabase.getRater(raterID).hasRating(movie)) {
                    numRater++;
                    totRating += weight*RaterDatabase.getRater(raterID).getRating(movie);
//                    totWeight += weight;
                }
            }
            if (numRater >= minNumRater) {
                simiRatingList.add(new Rating(movie, totRating/numRater));
            }
        }
        Collections.sort(simiRatingList,Collections.reverseOrder());
        return simiRatingList;
    }
}
