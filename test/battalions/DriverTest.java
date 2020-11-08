/*
 * School:         University of Alabama in Huntsville
 * Course Title:   Object-Oriented Programming in Java
 * Instructor:     Dr. Dan Rochowiak
 *
 * Course Number:  CS 321
 * Course Section: 01
 * Term:           Fall 2020
 *
 * Team:           10
 * Team Members:   Scott Clarke
 *                 Guess Crow
 *                 Blocker Griffin
 *                 Thomas Lemmons
 *                 Bryant Terry
 */
package battalions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the App class.
 * @author Scott
 */
public class DriverTest
{

    /**
     * Initializes a new instance of the DriverTest class.
     */
    public DriverTest()
    {
    }

    /**
     * Prepares this class for testing.
     */
    @BeforeClass
    public static void setUpClass()
    {
    }

    /**
     * Finalizes this class after testing.
     */
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Tests the App.main method.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        App.main(args);

        assertTrue(true);
    }

}
