package traveller.guide;

import java.util.*;

/** The Construction of a class that represents a business traveller. Extends from class Traveller.
* @since 31-05-2020
* @version 1.4
* @author it21871, it21846 */
public class Business extends Traveller{

    //=======================================================Business()=========================================================
    /** The constructor defines the fields which describe the traveller calling Traveller constructor.
     * @param name the name of the traveller.
     * @param birthDate the birth date of the traveller.
     * @param currentLat the current lattitude of the traveller.
     * @param currentLon the current longtitude of the traveller.
     * @param suggestedCities the cities that traveller wants.
     * @param travellerData the criteria traveller wants to his finally suggested city.
     * @param customerID the id of the traveller.
     * 
     */
    //==========================================================================================================================
    public Business(String name, Date birthDate, double currentLat, double currentLon, ArrayList<String> travellerData, 
            ArrayList<String> suggestedCities, int customerID) {
    	super(name, birthDate, currentLat, currentLon, travellerData, suggestedCities, customerID);
    }
    //======================================================End of Business()===================================================

    //========================================================Similarity()======================================================
    /** The method calculates similarity between city's and traveller's distance.
     * @param destinationCity the city object we want to find its similarity to the traveller.
     * @return a double number that represents percentage of similarity between city and traveller.
     */
    //==========================================================================================================================
    @Override
    public double Similarity(City destinationCity) {

        final int EARTHRADIUS = 6371;
	double dist;
	
	dist = distanceCalculation(getCurrentLat(), getCurrentLon(), destinationCity.getLat(), 
                destinationCity.getLon(), "K");
			
	return dist/EARTHRADIUS;
        
    }
    //====================================================End of Similarity()===================================================
		
    //===================================================distanceCalculation()==================================================
    /** The method calculates distance between city and traveller.
     * @param lat1 the current lattitude of traveller.
     * @param lon1 the current longtitude of traveller.
     * @param lat2 the lattitude of city.
     * @param lon2 the longtitude of city.
     * @param unit the unit in which the distance will be calculated.
     * @return a double number that represents the distance between city's and traveller's coordinators.
     */
    //==========================================================================================================================
    public static double distanceCalculation(double lat1, double lon1, double lat2, double lon2, String unit) {
	if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0.0;
	} else {
	    double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) 
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
                    * Math.cos(Math.toRadians(theta));
			    
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
			    
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } 
            else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
		    
            return (dist);
            
        }

    }
    //================================================End of distanceCalculation()==============================================
	
}//======================================================End of Class Business ======================================================
