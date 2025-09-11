package exception;

/** The Construction of a class that sets a new type of Exception.
* @since 31-05-2020
* @version 1.4
* @author it21871 */
public class WikipediaNoCityException extends Exception {
	
    private static final long serialVersionUID = 1L;
    static int numExceptions=0;
    private final String cityName;

    //==================================================WikipediaNoCityException()=================================================
    /** The constructor initializes all the necessary fields with specific values.
     * @param in_cityName the city.
     */
    //=============================================================================================================================
    public WikipediaNoCityException(String in_cityName) {
        numExceptions++;
        this.cityName=in_cityName;
    }
    //===============================================End of WikipediaNoCityException()=============================================

    //=========================================================getMessage()========================================================
    /** The method is implemented so as to specify the actions that thread will perform when starts running.
     * @return a message explaining what this exception represents
     */
    //=============================================================================================================================
    @Override
    public String getMessage() {
        return "There is not any wikipedia city with title "+cityName+".";
    }
    //=====================================================End of getMessage()=====================================================
    
}//=====================================================End of Class WikipediaNoCityException =================================================
