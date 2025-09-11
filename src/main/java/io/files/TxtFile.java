package io.files;

import java.io.*;
import java.util.*;

/** The Construction of a class that reads from and writes in a text file.
* @since 01-07-2020
* @version 1.5
* @author it21871 */
public class TxtFile {
    
    //==================================================read()================================================
    /** The method reads from a text file.
     * @param file the file from which data will be taken.
     * @return all the lines of the file.
     */
    //========================================================================================================
    public static ArrayList<String> read(String file) {
        
        ArrayList<String> data = new ArrayList<>();
        
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("1st time of execution! No file available");
        }
        
        return data;
        
    }
    //==============================================End of read()=============================================
    
    //==================================================write()===============================================
    /** The method writes in a text file.
     * @param file the file to which data will be written.
     * @param content the text that will be written in the file.
     */
    //========================================================================================================
    public static void write(String file, String content){
        
        ArrayList<String> data = read(file);
        Iterator<String> data_iterator = data.iterator();
        
        try {
            FileWriter myWriter = new FileWriter(file);
            PrintWriter pw = new PrintWriter(myWriter);
            while(data_iterator.hasNext())
                pw.println(data_iterator.next());
            if(!data.contains(content)) //to avoid dublicates
                pw.println(content);
            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    //=============================================End of write()=============================================
    
}//======================================================End of Class TxtFile ======================================================
