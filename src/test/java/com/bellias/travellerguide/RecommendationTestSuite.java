package com.bellias.travellerguide;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CollaborativeFilteringTest.class,
        RecommendedCityTest.class
})
public class RecommendationTestSuite {
}
