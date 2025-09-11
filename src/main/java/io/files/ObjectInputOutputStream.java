package io.files;

import java.io.*;
import java.util.*;
import traveller.guide.Traveller;

/** The Construction of a class that handles the ObjectInputStream and ObjectOutputStream classes so we can save the travellers into a file.
* @since 31-05-2020
* @version 1.4
* @author it21846, it21871 */
public class ObjectInputOutputStream {
    
    //=======================================================storeObjects()=====================================================
    /** The method handles ObjectOutputStream class, so we can store objects to file.
     * @param fileName the file that the ArrayList of Traveller objects will be saved.
     * @param array the ArrayList of Traveller objects that will be saved to file.
     * @throws java.io.IOException
     */
    //==========================================================================================================================
    public static void storeObjects(String fileName, ArrayList<Traveller> array) throws IOException {
	
        File file = new File(fileName);
	FileOutputStream out = new FileOutputStream(file, true);
	ObjectOutputStream oout = new ObjectOutputStream(out);

	oout.writeObject(array); // write something in the file
	oout.flush();
	oout.close();
	System.out.println("Data are written in the file.");
        
    }
    //====================================================End of storeObjects()================================================
    
    //=====================================================retrieveObjects()===================================================
    /** The method handles ObjectInputStream class, so we can retrieve objects from file.
     * @param fileName the file that will be read to retrieve ArrayList of Traveller objects.
     * @param array the ArrayList of Traveller objects that will get filled from file.
     * @return the ArrayList of Traveller objects retrieved from the file.
     * @throws java.io.FileNotFoundException 
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    //==========================================================================================================================
    public static ArrayList<Traveller> retrieveObjects(String fileName, ArrayList<Traveller> array) throws 
            FileNotFoundException, IOException, ClassNotFoundException {
             
        try{
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            array = (ArrayList<Traveller>) ois.readObject();
            
            ois.close();
                       
        }catch(NoSuchElementException | IOException | ClassNotFoundException ex){
            array = new ArrayList<>();
        }
        
        Traveller.setTravellersNumber(array.size());
        
        return array;
        
    }
    //==================================================End of retrieveObjects()===============================================
    
}//==================================================End of Class ObjectInputOutputStream ==============================================
