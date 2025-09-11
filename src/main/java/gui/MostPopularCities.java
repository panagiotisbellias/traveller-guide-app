package gui;

import io.files.TxtFile;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;
import traveller.guide.PopularCity;

/**
 *
 * @author it21871
 */
/* The construction of a class that finds and shows to the user the most popular city according to event. */
public class MostPopularCities implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        
        /* Get cities from user and calculate the popularity */
        PopularCity popularCity = citiesProcessing();
        
        if(GUI.getCitiesText().isEmpty()){
            return;
        }
            
        String[] userCities = GUI.getCitiesText().split("\n");
        
        for(String city: userCities){
            String[] cityString = city.split(", ");
            if(cityString.length < 2) return;
        }
            
        /* Create a message dialog to show user the most popular city of those who entered */
        JOptionPane.showMessageDialog(GUI.getFRAME(), "Η δημοφιλέστερη πόλη που δώσατε!\n" + 
                popularCity.getName() + ", " + popularCity.getCountry());
               
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
     
    /* Take cities from text field and make PopularCity objects to handle them properly */
    private static PopularCity citiesProcessing(){
        
        /* ArrayList for PopularCity objects */
        ArrayList<PopularCity> popularCities = new ArrayList<>();
        
        String GUIcities = GUI.getCitiesText();
        if(GUIcities.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your cities and try again!",  "No cities found", 
                        JOptionPane.ERROR_MESSAGE);
            return new PopularCity();
        }
        
        String[] userCities = GUIcities.split("\n");
        
        for(String city: userCities){
            
            String[] cityString = city.split(", ");
            
            if(cityString.length < 2) {
                JOptionPane.showMessageDialog(null, "Not right format"
                        + "\nTry again entering city_name, city_country divided with comma",  "Wrong City Input", 
                        JOptionPane.ERROR_MESSAGE);
                return new PopularCity();
            }
            
            PopularCity popularCity = new PopularCity(cityString[0], cityString[1]);
            popularCity.calculatePopularity(); //We call this method and the popularity value of this city is defined
            
            popularCities.add(popularCity);
            
        }
        
        /* Save the popular cities to a file */
        popularCitiesToFile(popularCities);
                
        return maxPopularity(popularCities);
        
    }
    
    /* Save cities to the file */
    private static void popularCitiesToFile(ArrayList<PopularCity> popularCities){
        
        String fileName = "popular_cities.txt";
        
        Iterator<PopularCity> pc = popularCities.iterator();
        while(pc.hasNext()){
            PopularCity popularCity = pc.next();
            String content = popularCity.getName() + ",\t" + popularCity.getCountry() + "\t\t" +
                    popularCity.getPopularity();
            TxtFile.write(fileName, content);
        }
        
    }
    
    /* Find the city with the most popularity */
    private static PopularCity maxPopularity(ArrayList<PopularCity> popularCities){
        
        PopularCity pc = new PopularCity(); 
        
        if(popularCities.size() >= 2)
            for(int i = 0;i<popularCities.size()-1;i++) {
                if(popularCities.get(i).getPopularity() <= popularCities.get(i+1).getPopularity()){
                    pc = popularCities.get(i+1);
                } else {
                    pc = popularCities.get(i);
                }
            }
        else if(!popularCities.isEmpty())
            pc = popularCities.get(0);
        
        return pc;
        
    }
    
}
