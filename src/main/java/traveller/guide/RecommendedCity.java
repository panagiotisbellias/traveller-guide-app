package traveller.guide;

/** The Construction of a class that represents a city which gets ranking.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class RecommendedCity {
    
    String City;
    int rank;
    
    //====================================================RecommendedCity()=====================================================
    /** The constructor defines the fields which describe the city with specific values.
     * @param city the city in which refer to.
     * @param rank the rank which this city will have.
     */
    //==========================================================================================================================
    public RecommendedCity(String city, int rank) {
        super();
        City = city;
        this.rank = rank;
    }
    //==================================================End of RecommendedCity()================================================
    
    //Getters and setters
    
    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }
    
    /**
     * @param city the city in which refer to.
     */
    public void setCity(String city) {
        City = city;
    }
    
    /**
     * @return the Rank
     */
    public int getRank() {
        return rank;
    }
    
    /**
     * @param rank the rank which this city will have.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    
}//======================================================End of Class ReoommendedCity ======================================================
