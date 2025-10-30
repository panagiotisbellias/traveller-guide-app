package com.bellias.rest;

import com.bellias.exception.WikipediaNoArticleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A thread class responsible for retrieving data from the Wikipedia API.
 *
 * @author Panagiotis Bellias
 */
public class WikiThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(WikiThread.class);
    private Thread t;
    private final String threadName;
    private final String city;
    private String wikiData;

    /**
     * Initializes a new WikiThread with the given parameters.
     *
     * @param name the thread name
     * @param odrCity the target city
     */
    public WikiThread(String name, String odrCity) {
        this.threadName = name;
        this.city = odrCity;
        System.out.println("Creating " + threadName);
    }

    /**
     * Executes the thread logic for retrieving Wikipedia data.
     */
    @Override
    public void run() {
        OpenDataRest odr;
        System.out.println("Running " + threadName);
        try {
            odr = new OpenDataRest();
            wikiData = odr.retrieveWikipedia(city);
        } catch (IOException | WikipediaNoArticleException e) {
            log.error(String.valueOf(e));
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    /**
     * Starts the thread if not already started.
     */
    @Override
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    /**
     * Returns the retrieved Wikipedia data.
     *
     * @return the Wikipedia data string
     */
    public String getWikiData() {
        return wikiData;
    }
}
