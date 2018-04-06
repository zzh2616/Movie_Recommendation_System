import java.util.ArrayList;
import java.util.Collections;

public class RecommendationRunner implements Recommender {

//	Generate a list of top rated movies in the database
    @Override
    public ArrayList<String> getItemsToRate() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());
//        shuffle the list to generate random movies for users to rate
        Collections.shuffle(movieList);
//      maximum number of movies showing on the page
        int maxListLen = 15;
        movieList = new ArrayList<String>(movieList.subList(0, maxListLen));
   	
        return movieList;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings movieRater = new FourthRatings();
        int numSimilarRaters = 20;
        int minNumRater = 5;
        ArrayList<Rating> ratingList = movieRater.getSimilarRatings(webRaterID, numSimilarRaters, minNumRater);
        int minListLen = 1;
        int maxListLen = 15;
//        alerts when no movie recommendations are given
        if (ratingList.size() < minListLen) {
            System.out.print(
                    "<script type=\"text/javascript\">window.alert(\"No Recommendations Matches Your Ratings\")</script>"
                    + "<body> <style> font-size: large; font-weight: bold; </style> <center> Please go back and rate again... </center> </body>");
            return ;
        }
        ArrayList<String> movieList = new ArrayList<String>();
        if (ratingList.size() < maxListLen) {
            maxListLen = ratingList.size();
        }
        for (int i = 0; i < maxListLen; i++) {
            movieList.add(ratingList.get(i).getItem());
        }
        System.out.print(
                "<style>" + 
                "TABLE {" + 
                "   border: 1px solid black;" + 
                "   border-collapse: collapse;" + 
                "   margin: 0 auto;" + 
                "}" + 
                "TD {" + 
                "   border: 1px solid black;" + 
                "   padding: 5px;" + 
                "   vertical-align: middle;" + 
                "}" + 
                "TH {" + 
                "   border: 1px solid black;" + 
                "   padding: 5px;" + 
                "}" + 
                "TR:nth-child(even) {" + 
                "    background-color: #f2f2f2" + 
                "}" + 
                "</style>" +
                "<table>" + 
                "<tr>" + 
                "<th>  </th>" + 
                "<th>Movie</th>" +  
                "<th>Genre</th>" + 
                "<th>Director</th>" +
                "<th>Minutes</th>" +
                "</tr>");
        /* KNOWN BUG OF DUKELEARNTOPROGRAM WEBSITE!!!
         * getDirector() = minutes of the movie
         * getCountry() = genres of the movie
         * getGenres() = directors of the movie*/
        for (String movie : movieList) {
            System.out.print(
                    "<tr><td>" + 
                    "  <a href=" + MovieDatabase.getPoster(movie) + ">" + 
                    "  <img src=" + MovieDatabase.getPoster(movie) + " height=\"50\" align=\"right\">" + 
                    "  </a>" + 
                    "</td>" +
                    "<td>" + 
                      MovieDatabase.getYear(movie) + "&nbsp;&nbsp; <a href=\"http://www.imdb.com/title/tt" + movie + "/\">" + MovieDatabase.getTitle(movie) + "</a>" + 
                    "</td>" +
                    "<td>" + 
                      MovieDatabase.getCountry(movie) +
                    "</td>" +
                    "<td>" + 
                      MovieDatabase.getGenres(movie) +
                    "</td>" +
                    "<td>" + 
                      MovieDatabase.getMinutes(movie) + 
                    "</td>" + 
                    "</tr>");
        }
        System.out.print("</table>");
    }

}