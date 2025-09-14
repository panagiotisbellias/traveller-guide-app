package com.bellias.exception;

/** The Construction of a class that sets a new type of Exception.
* @since 31-05-2020
* @version 1.4
* @author it21871 */
public class WrongNumberInputException extends Exception {
    
    private static final long serialVersionUID = 1L;
    static int numExceptions=0;
    
    //==================================================WrongNumberInputException()=================================================
    /** The constructor increases all the necessary fields.
     */
    //==============================================================================================================================
    public WrongNumberInputException() {
	numExceptions++;
    }
    //===============================================End of WrongNumberInputException()=============================================

    //==========================================================getMessage()========================================================
    /** The method is implemented so as to specify the actions that thread will perform when starts running.
     * @return a message explaining what this exception represents
     */
    //==============================================================================================================================
    @Override
    public String getMessage(){
        return "The number is not allowed.";
    }
    //=====================================================End of getMessage()======================================================
    
}//=====================================================End of Class WrongNumberInputException =================================================
