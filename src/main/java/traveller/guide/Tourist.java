package traveller.guide;

import java.util.*;
import open.data.rest.OpenDataRest;

/** The Construction of a class that represents a tourist traveller. Extends from class Traveller.
* @since 31-05-2020
* @version 1.4
* @author it21871, it21846 */
public class Tourist extends Traveller{

    //=======================================================Tourist()=========================================================
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
    public Tourist(String name,Date birthDate, double currentLat, double currentLon, ArrayList<String> travellerData, 
            ArrayList<String> suggestedCities, int customerID) {
        super(name, birthDate, currentLat, currentLon, travellerData, suggestedCities, customerID);
    }
    //======================================================End of Tourist()====================================================
	
    //========================================================Similarity()======================================================
    /** The method calculates similarity between city's data and travellers' preferences but according to the frequency to
     * simulate the number of them in a city.
     * @param destinationCity the city object we want to find its similarity to the traveller.
     * @return a double number that represents percentage of similarity between city and traveller.
     */
    //==========================================================================================================================
    @Override
    public double Similarity(City destinationCity) {
        
        double similarityValue;
		
        ArrayList<String> travelerList = listOfDistinctWords(getTravellerData());
	String cityList = destinationCity.getCityData();
	int similarityCounter = 0;
	int totalFrequency = 0; 
	int frequency;
	ArrayList<Integer> frequencyTable = new ArrayList<>();  
	
	for(int i = 0; i < travelerList.size(); i++) {
            if(cityList.contains(travelerList.get(i))) {
                similarityCounter++;
		frequency = OpenDataRest.countCriterionfCity(cityList, travelerList.get(i));
		frequencyTable.add(frequency);
		totalFrequency += frequency; 
            }
        }
		
	similarityValue = similarityCounter/travelerList.size();
	
	for(int j = 0; j < frequencyTable.size(); j++) {
            //Handle ArithmeticException...
            try{
                similarityValue *= frequencyTable.get(j)/totalFrequency;
            }catch(ArithmeticException e){
                similarityValue = 0.0;
                break;
            }
	}
		
	return similarityValue;
        
    }
    //====================================================End of Similarity()===================================================
	
}//======================================================End of Class Tourist ======================================================
