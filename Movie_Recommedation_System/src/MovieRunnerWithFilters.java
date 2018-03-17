import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAvergeRatings() {
        MovieDatabase.initialize();
        ThirdRatings movieRater = new ThirdRatings();     
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + movieRater.getRaterSize());
        ArrayList<Rating> ratingList = movieRater.getAverageRatings(35);
        System.out.println(ratingList.size());
        Collections.sort(ratingList);
        for (Rating rating : ratingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAvergeRatingsByFilter() {
        MovieDatabase.initialize();
        ThirdRatings movieRater = new ThirdRatings();     
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + movieRater.getRaterSize());
        Filter f1 = new YearAfterFilter(2000);
        Filter f2 = new GenreFilter("Comedy");
        Filter f3 = new MinutesFilter(105, 135);
        Filter f4 = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        AllFilters af = new AllFilters();
//        af.addFilter(f1);
//        af.addFilter(f1);
        ArrayList<Rating> ratingList = movieRater.getAverageRatingsByFilter(35,af);
        System.out.println(ratingList.size());
//        Collections.sort(ratingList);
//        for (Rating rating : ratingList) {
//            System.out.println(rating.getItem() + "\t" + rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
//        }
    }
    
    public static void main(String[] args) {
        MovieRunnerWithFilters test = new MovieRunnerWithFilters();
        test.printAvergeRatings();
//        test.printAvergeRatingsByFilter();
    }
    
}
