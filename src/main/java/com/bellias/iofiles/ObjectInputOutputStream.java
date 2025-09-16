package com.bellias.iofiles;

import com.bellias.travellerguide.Traveller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

}//==================================================End of Class ObjectInputOutputStream ==============================================
