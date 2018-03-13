import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRating {
	public ArrayList<Movie> loadMovies(String fname){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		String path = "./data/" + fname;
		FileResource fr = new FileResource(path);
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord record: parser) {
			Movie newMovie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
			movieList.add(newMovie);
		}
		return movieList;
	}
	
	public ArrayList<Rater> loadRaters(String fname){
		ArrayList<Rater> raterList = new ArrayList<Rater>();
		String path = "./data/" + fname;
		FileResource fr = new FileResource(path);
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord record: parser) {
			Rater newRater = new Rater(record.get("rater_id"));
			if (raterList.contains(newRater)) {
				raterList.get(raterList.indexOf(newRater)).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
			} else {
				newRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
				raterList.add(newRater);
			}
		}
		return raterList;		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
