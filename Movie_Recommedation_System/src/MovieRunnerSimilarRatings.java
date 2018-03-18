import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAvergeRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings movieRater = new FourthRatings();     
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());
        ArrayList<Rating> ratingList = movieRater.getAverageRatings(35);
        System.out.println(ratingList.size());
        Collections.sort(ratingList);
        for (Rating rating : ratingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAvergeRatingsByFilter() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings movieRater = new FourthRatings();     
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());
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
    
    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings movieRater = new FourthRatings();     
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());
        String id = "65";
        int numSimilarRaters = 20;
        int minNumRater = 5;
        ArrayList<Rating> ratingList = movieRater.getSimilarRatings(id, numSimilarRaters, minNumRater);
        System.out.println(ratingList.size());
        for (Rating rating : ratingList) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByFilter() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings movieRater = new FourthRatings();     
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new GenreFilter("Drama");
        Filter f3 = new MinutesFilter(70, 200);
        Filter f4 = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        AllFilters af = new AllFilters();
        af.addFilter(f1);
        af.addFilter(f3);
        String id = "314";
        int numSimilarRaters = 10;
        int minNumRater = 5;
        ArrayList<Rating> ratingList = movieRater.getSimilarRatingsByFilter(id, numSimilarRaters, minNumRater, af);
        System.out.println(ratingList.size());
        for (Rating rating : ratingList) {
            System.out.println(rating.getItem() + "\t" + rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public static void main(String[] args) {
        MovieRunnerSimilarRatings test = new MovieRunnerSimilarRatings();
//        test.printAvergeRatings();
//        test.printAvergeRatingsByFilter();
//        test.printSimilarRatings();
        test.printSimilarRatingsByFilter();
    }

}
