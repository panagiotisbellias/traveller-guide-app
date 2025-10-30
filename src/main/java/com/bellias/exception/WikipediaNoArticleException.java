package com.bellias.exception;

import java.io.Serial;

/**
 * Exception thrown when a Wikipedia article cannot be found for a specified city.
 *
 * <p>This exception is typically used when attempting to fetch or process data from
 * Wikipedia for a city name that does not correspond to an existing article.</p>
 *
 * @author Panagiotis Bellias
 * @author John Violos
 */
public class WikipediaNoArticleException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Counter tracking how many times this exception has been created. */
    private static int numExceptions = 0;

    /** The name of the city for which no Wikipedia article was found. */
    private final String cityName;

    /**
     * Constructs a new {@code WikipediaNoArticleException} for the given city.
     *
     * @param cityName the name of the city without a corresponding Wikipedia article
     */
    public WikipediaNoArticleException(String cityName) {
        numExceptions++;
        this.cityName = cityName;
    }

    /**
     * Returns a descriptive message indicating that no article exists for the specified city.
     *
     * @return a human-readable error message
     */
    @Override
    public String getMessage() {
        return "No Wikipedia article found with title \"" + cityName + "\".";
    }

    /**
     * Returns the total number of {@code WikipediaNoArticleException} instances created.
     *
     * @return number of times this exception has been instantiated
     */
    public static int getNumExceptions() {
        return numExceptions;
    }
}
