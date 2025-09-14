package com.bellias.rest;

import com.bellias.exception.WikipediaNoArticleException;

import java.io.IOException;

/** The Construction of a class that uses multithreading for retrieving MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author it21846 */
public class WikiThread extends Thread {
    
    private Thread t;
    private final String threadName;
    private static OpenDataRest odr;
    private final String city;
    private String owm;

    //=======================================================WikiThread()======================================================
    /** The constructor initializes all the necessary fields with specific values.
     * @param name the name of thread.
     * @param odrCity the searching city's name.
     */
    //=========================================================================================================================
    public WikiThread(String name, String odrCity){
        threadName = name;
        city = odrCity;
        System.out.println("Creating " + threadName);
    }
    //====================================================End of WikiThread()==================================================

    //===========================================================run()=========================================================
    /** The method is implemented so as to specify the actions that thread will perform when starts running.
     */
    //=========================================================================================================================
    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            odr = new OpenDataRest();
            owm = odr.RetrieveWikipedia(city);
        } catch (IOException | WikipediaNoArticleException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
        // TODO Auto-generated catch block
        System.out.println("Thread " + threadName + " exiting.");
    }
    //=======================================================End of run()======================================================

    //===========================================================start()=======================================================
    /** The method is implemented so as to specify the conditions that thread will starts running.
     */
    //=========================================================================================================================
    @Override
    public void start() {
        System.out.println("Starting " + threadName);
        if(t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
    //=====================================================End of start()======================================================

    /**
     * @return the WikiData
     */
    public String getWikiData() {
        return owm;
    }
    
}//=====================================================End of Class WikiThread =================================================