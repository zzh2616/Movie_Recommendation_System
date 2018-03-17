import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public void printAvergeRatings() {
        SecondRatings movieRater = new SecondRatings();
        System.out.println("number of movies: " + movieRater.getMovieSize());
        System.out.println("number of raters: " + movieRater.getRaterSize());
        ArrayList<Rating> ratingList = movieRater.getAverageRatings(12);
        System.out.println(ratingList.size());
        Collections.sort(ratingList);
        for (Rating rating : ratingList) {
            System.out.println(rating.getValue() + "\t" + movieRater.getTitle(rating.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings movieRater = new SecondRatings();
        String title = "Vacation";
        ArrayList<Rating> ratingList = movieRater.getAverageRatings(3);
        for (Rating rating : ratingList) {
            if (movieRater.getID(title).equals(rating.getItem())) {
                System.out.println(rating.getValue());
            }          
        }
    } 
    
    public static void main(String[] args) {
        MovieRunnerAverage test = new MovieRunnerAverage();
        test.printAvergeRatings();
//        test.getAverageRatingOneMovie();
    }

}
