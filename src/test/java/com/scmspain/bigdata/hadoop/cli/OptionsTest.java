package com.scmspain.bigdata.hadoop.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsTest
{
    private Options options;

    @Before
    public void setUp() throws Exception
    {
        options = new Options();
    }

    @After
    public void tearDown() throws Exception
    {
        options = null;
    }

    @Test
    public void testAddOption() throws Exception
    {
        options.addOption("test", "t", "test");

        assertEquals(
                "An option has been added",
                (Integer) 1,
                options.getOptionsSize()
        );
    }

    @Test
    public void testGetOptionForAnAddedOptionWithLongKey() throws Exception
    {
        options.addOption("test", "t", "test");

        assertEquals(
                "Added option can be found",
                OptionDefinition.class,
                options.getOption("test").getClass()
        );
    }

    @Test
    public void testGetOptionForAnAddedOptionWithShortKey() throws Exception
    {
        options.addOption("test", "t", "test");

        assertEquals(
                "Added option can be found",
                OptionDefinition.class,
                options.getOption("t").getClass()
        );
    }

    @Test(expected = OptionNotFoundException.class)
    public void testGetOptionForANonAddedOption() throws Exception
    {
        options.getOption("non-existing");
    }

}