package com.bellias.travellerguide;

import java.util.Objects;

/**
 * The Construction of a class that represents a city which gets ranking.
 *
 * @author Panagiotis Bellias, John Violos
 */
public class RecommendedCity {

    String City;
    double rank;

    /**
     * The constructor defines the fields which describe the city with specific values.
     *
     * @param city the city in which refer to.
     * @param rank the rank which this city will have.
     */
    public RecommendedCity(String city, double rank) {
        super();
        City = city;
        this.rank = rank;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param city the city in which refer to.
     */
    public void setCity(String city) {
        City = city;
    }

    /**
     * @return the Rank
     */
    public double getRank() {
        return rank;
    }

    /**
     * @param rank the rank which this city will have.
     */
    public void setRank(double rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "RecommendedCity{name='" + City + "', rank=" + rank + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendedCity that = (RecommendedCity) o;
        return Double.compare(that.rank, rank) == 0 && City.equals(that.City);
    }

    @Override
    public int hashCode() {
        return Objects.hash(City, rank);
    }

}
