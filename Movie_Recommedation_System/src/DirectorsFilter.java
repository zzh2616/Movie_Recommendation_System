
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors.split("\\s*,\\s*");
    }
    @Override
    public boolean satisfies(String id) {
        boolean ans = false;
        for (int i = 0; i < myDirectors.length; i++) {
            if (MovieDatabase.getDirector(id).contains(myDirectors[i])) {
                ans = true;
            }
        }
        return ans;
    }

}
