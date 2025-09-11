package traveller.guide;

import java.io.Serializable;
import java.util.*;

/** The Construction of a class that represents a traveller. The class implements Comparable and Serializable interface.
* @since 31-05-2020
* @version 1.4
* @author it21871, it21846 */
public class Traveller implements Comparable<Traveller>, Serializable{ 
    
    private static final long serialVersionUID = 1L;
    
    //Travellers' characteristics...
    private String name;
    private Date birthDate;
    private int age,customerID;
    private double currentLat, currentLon;
    private ArrayList<String> suggestedCities;
    private ArrayList<String> travellerData; //Travellers' criteria
    private static int travellersNumber; //Number of travellers
    private String visit; //Holds city we recommended him
	
    //=========================================================compareTo()======================================================
    /** The method is implemented and used to sort traveller objects by age.
     * @param tr object of class Traveller we want to compare.
     * @return the difference of current Traveller's age and given traveller's age.
     */
    //==========================================================================================================================
    @Override
    public int compareTo(Traveller tr){
        return this.age - tr.age;
    }
    //======================================================End of compareTo()==================================================
    
    //Travellers' constructors
    //=======================================================Traveller()========================================================
    /** The constructor defines the fields which describe the traveller with certain values.
     * @param name the name of the traveller.
     * @param birthDate the birth date of the traveller.
     * @param currentLat the current lattitude of the traveller.
     * @param currentLon the current longtitude of the traveller.
     * @param suggestedCities the cities that traveller wants.
     * @param travellerData the criteria traveller wants to his finally suggested city.
     * @param visit the city that traveller was recommended to finally.
     * @param customerID the id of the traveller.
     * 
     */
    //==========================================================================================================================
    public Traveller(String name, Date birthDate, double currentLat, double currentLon ,ArrayList<String> suggestedCities,
            ArrayList<String> travellerData, String visit, int customerID) {
        
        this.name = name;
        this.birthDate = birthDate;
        age = findAge(birthDate);
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.suggestedCities = suggestedCities;
        this.travellerData = travellerData;
        this.visit = visit;
        this.customerID = customerID;
                
    }
    //======================================================End of Traveller()==================================================
    
    //=======================================================Traveller()========================================================
    /** The most-used constructor defines the fields which describe the traveller with certain values except field "visit".
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
    public Traveller(String name, Date birthDate, double currentLat, double currentLon, ArrayList<String> travellerData,
            ArrayList<String> suggestedCities, int customerID) {
        
        this.name = name;
        this.birthDate = birthDate;
        age = findAge(birthDate);
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.travellerData = travellerData;
        this.suggestedCities = suggestedCities;
        this.customerID = customerID;
        visit = "";
        
    }
    //======================================================End of Traveller()==================================================
    
    //=======================================================Traveller()========================================================
    /** The constructor defines the fields which describe the traveller with empty values.
     */
    //==========================================================================================================================
    public Traveller() {
        
        name = "";
        birthDate = new Date();
        age = 0;
        currentLat = 0.0;
        currentLon = 0.0;
        travellerData = new ArrayList<>();
        suggestedCities = new ArrayList<>();
        customerID = 0;
        visit = "";
    
    }
    //======================================================End of Traveller()==================================================

    //Getters and setters
    
    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the traveller.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the BirthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the the birth date of the traveller.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the Age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age of the traveller.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the CustomerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the id of the traveller.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the currentLat
     */
    public double getCurrentLat() {
        return currentLat;
    }

    /**
     * @param currentLat the current lattitude of the traveller.
     */
    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    /**
     * @return the currentLon
     */
    public double getCurrentLon() {
        return currentLon;
    }

    /**
     * @param currentLon the current longtitude of the traveller.
     */
    public void setCurrentLon(double currentLon) {
        this.currentLon = currentLon;
    }

    /**
     * @return the suggestedCities
     */
    public ArrayList<String> getSuggestedCities() {
        return suggestedCities;
    }

    /**
     * @param suggestedCities the cities that traveller wants.
     */
    public void setSuggestedCities(ArrayList<String> suggestedCities) {
        this.suggestedCities = suggestedCities;
    }
    
    /**
     * @return the travellerData
     */
    public ArrayList<String> getTravellerData() {
        return travellerData;
    }

    /**
     * @param travellerData the criteria traveller wants to his finally suggested city.
     */
    public void setTravellerData(ArrayList<String> travellerData) {
        this.travellerData = travellerData;
    }

    /**
     * @return the TravellersNumber
     */
    //Travellers' number's getter and setter...
    public static int getTravellersNumber() {
        return travellersNumber;
    }

    /**
     * @param travellersNumber the number of travellers.
     */
    public static void setTravellersNumber(int travellersNumber) {
        Traveller.travellersNumber = travellersNumber;
    }
    
    /**
     * @return the Visit
     */
    //Variable visit's getter and setter...
    public String getVisit() {
        return visit;
    }

    /**
     * @param visit the city that traveller was recommended to finally.
     */
    public void setVisit(String visit) {
        this.visit = visit;
    }
    
    //=========================================================Similarity()======================================================
    /** The method calculates similarity between city's data and travellers' preferences.
     * @param city the city object we want to find its similarity to the traveller.
     * @return a double number that represents percentage of similarity between city and traveller.
     */
    //===========================================================================================================================
    public double Similarity(City city) {
        
	double similarityValue;
	ArrayList<String> travellerList = new ArrayList<>();
	travellerList.addAll(listOfDistinctWords(travellerData));
	String cityData = city.getCityData();
	int similarityCounter = 0;
		
        Iterator<String> t = travellerList.iterator();
        while(t.hasNext()){
            String travellerCriterias = t.next();
            if(cityData.contains(travellerCriterias)){
                similarityCounter++;
            }
        }
        
        //Handle ArithmeticException...
        try{
            similarityValue = similarityCounter/travellerList.size();
        }catch(ArithmeticException e){
            similarityValue = 0.00;
        }
            
        return similarityValue;
        
    }
    //=====================================================End of Similarity()===================================================
	
    //====================================================listOfDistinctWords()==================================================
    /** The method removes the dublicate words of an ArrayList with String elements.
     * @param str the ArrayList with String elements we want to remove the dublicates.
     * @return a new ArrayList with String elements without dublicates.
     */
    //===========================================================================================================================
    public static ArrayList<String> listOfDistinctWords(ArrayList<String> str) {
    
        ArrayList<String> list = new ArrayList <>();
        
        Iterator<String> str_iter = str.iterator();
        while(str_iter.hasNext()){
            String string = str_iter.next();
            if(!(list.contains(string))){
                list.add(string);
            }
        }
        
        return list;
        
    }
    //================================================End of listOfDistinctWords()===============================================
	
    //=======================================================CompareCities()=====================================================
    /** The method compares the cities given as argument and finds the city with the maximum similarity.
     * @param Cities the ArrayList with City elements we want to compare.
     * @return the city object with the maximum similarity.
     */
    //===========================================================================================================================
    public City CompareCities(ArrayList<City> Cities) {
        
        City withMostSimilarityCity = null; 
        
        if(Cities.size() >= 2)
            for(int i = 0;i<Cities.size()-1;i++) {
                if(Similarity(Cities.get(i)) <= Similarity(Cities.get(i+1))) {
                    withMostSimilarityCity = Cities.get(i+1);
                } else {
                    withMostSimilarityCity = Cities.get(i);
                }
            }
        else if(!Cities.isEmpty())
            withMostSimilarityCity = Cities.get(0);//Logical Error
        
        return withMostSimilarityCity;
        
    }
    //===================================================End of CompareCities()==================================================
    
    //=======================================================CompareCities()=====================================================
    /** The method excludes the cities with the non-wanted weather and then applies overloading to compare them.
     * @param weather the weather user doesn't want his city to have.
     * @param cities the ArrayList with City elements we want to compare.
     * @return the city object with the maximum similarity.
     */
    //===========================================================================================================================
    public City CompareCities(String weather, ArrayList<City> cities) {
        
        Iterator<City> cities_iterator = cities.iterator();
        
        ArrayList<City> NotExcludedCities = new ArrayList<>();
        City withMostSimilarityCity;
	
        while(cities_iterator.hasNext()){
            City city = cities_iterator.next();
            if(!(city.getWeather()).equals(weather)){
                NotExcludedCities.add(city);
            }
        }
        
        withMostSimilarityCity = CompareCities(NotExcludedCities);
		
        return withMostSimilarityCity;
        
    }
    //===================================================End of CompareCities()==================================================
	
    //the methods below is for removing dublicates from the printing of sorted travellers
    //===========================================================equals()========================================================
    /** The method compares two traveller objects setting the uniqueness of Traveller objects via traveller name.
     * @param o the object which want to compare with the current object.
     * @return the result of comparing the object given with the current object only by traveller name.
     */
    //===========================================================================================================================
    @Override
    public boolean equals(Object o){
        
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Traveller or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Traveller)) { 
            return false; 
        } 
          
        // typecast o to Traveller so that we can compare data  
        Traveller traveller = (Traveller) o; 
          
        // Compare the data and return accordingly  
        return getName().equals(traveller.getName());
        
    } 
    //========================================================End of equals()====================================================

    //==========================================================hashCode()=======================================================
    /** Auto-generated method to calculate object's hashcode according its fields.
     * @return the result of calculating object's hashcode.
     */
    //===========================================================================================================================
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.age;
        hash = 59 * hash + this.customerID;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.currentLat) ^ 
                (Double.doubleToLongBits(this.currentLat) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.currentLon) ^ 
                (Double.doubleToLongBits(this.currentLon) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.travellerData);
        hash = 59 * hash + Objects.hashCode(this.suggestedCities);
        hash = 59 * hash + Objects.hashCode(this.visit);
        return hash;
    }
    //=======================================================End of hashCode()===================================================
        
    //===================================================findQualifiedCustomer()=================================================
    /** The method finds the best traveller to take free ticket to a certain city.
     * @param travellers the travellers we want to examine.
     * @param city the city which the free ticket is about.
     * @return the proper Traveller object.
     */
    //===========================================================================================================================
    public static Traveller findQualifiedCustomer(ArrayList<Traveller> travellers, City city) {
        
    	Traveller qualifiedCustomer = null;
        
        if(travellers.size() >= 2)
            for(int i = 1; i < travellers.size(); i++) {
                if(travellers.get(i-1).Similarity(city) == travellers.get(i).Similarity(city) ) {
                    if(travellers.get(i-1).getCustomerID() < travellers.get(i).getCustomerID()) {
                        qualifiedCustomer = travellers.get(i-1);
                    } else {
                        qualifiedCustomer = travellers.get(i);
                    }
                } else if(travellers.get(i-1).Similarity(city) < travellers.get(i).Similarity(city) ) {
                    qualifiedCustomer = travellers.get(i);
                } else if(travellers.get(i-1).Similarity(city) > travellers.get(i).Similarity(city)) {
                    qualifiedCustomer = travellers.get(i-1);
                } else {
                    qualifiedCustomer = null;
                }
            }
        else if(!travellers.isEmpty())
            qualifiedCustomer = travellers.get(0);
		
    	return qualifiedCustomer;
        
    }
    //===============================================End of findQualifiedCustomer()==============================================
    
    //=======================================================setAgesForAll()=====================================================
    /** The method finds the best traveller to take free ticket to a certain city.
     * @param travellers the travellers we want to set their ages.
     */
    //===========================================================================================================================
    public static void setAgesForAll(ArrayList<Traveller> travellers){
                
        Iterator<Traveller> t = travellers.iterator();
        while(t.hasNext()){

            Traveller tr = t.next();
            tr.setAge(findAge(tr.getBirthDate()));

        }
        
    }
    //====================================================End of setAgesForAll()=================================================
    
    //==========================================================findAge()========================================================
    /** The method calculate the age according to the local date-time and birth-date.
     * @param birthDate the birth-date needed.
     */
    //===========================================================================================================================
    private static int findAge(Date birthDate){
        
        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        //Get difference between years
        int years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;

        //Get difference between months
        int months = currMonth - birthMonth;

        //if month difference is in negative then reduce years by one and calculate the number of months.
        if (months < 0) {
           years--;
           months = 12 - birthMonth + currMonth;
           if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
              months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
           years--;
           months = 11;
        }

        //Calculate the days
        int days;
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
           days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
           int today = now.get(Calendar.DAY_OF_MONTH);
           now.add(Calendar.MONTH, -1);
           days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } 
        else {
           days = 0;
           if (months == 12)
              years++;
        }

        return years;
        
    }
    //======================================================End of findAge()===================================================
    
}//======================================================End of Class Traveller ======================================================
