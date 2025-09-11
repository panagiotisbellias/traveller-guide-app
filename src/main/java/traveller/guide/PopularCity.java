package traveller.guide;

import exception.WikipediaNoArticleException;
import java.io.IOException;
import open.data.rest.OpenDataRest;

/**
 *
 * @author Panagiotis Bellias
 */
/* The construction of a class that handle cities as objects*/
public class PopularCity {
    
    /* Attributes */
    private String name, country;
    private int popularity;

    /* Constructors */
    public PopularCity(String name, String country, int popularity) {
        this.name = name;
        this.country = country;
        this.popularity = popularity;
    }
    
    public PopularCity(String name, String country){
        this.name = name;
        this.country = country;
    }
    
    public PopularCity(){
        name = "";
        country = "";
        popularity = 0;
    }

    /* Getters and setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    
    public void calculatePopularity(){
        
        OpenDataRest odr = new OpenDataRest();
        
        String article = ""; 
        try {
            article = odr.RetrieveWikipedia(name);
        } catch (IOException | WikipediaNoArticleException ex) {
            System.out.println(ex);
        }
        
        popularity = countTotalWords(article);
        
    }
    
    /** Counts all words in the input String.
     * @param str The input String.
     * @return An integer, the number of all words.
     * @author John Violos
     */	
    public static int countTotalWords(String str) {	
        String s[]=str.split(" ");
        return s.length;
    }
    
    @Override
    public String toString(){
        return name + ", " + country + ", " + popularity;
    }

}
