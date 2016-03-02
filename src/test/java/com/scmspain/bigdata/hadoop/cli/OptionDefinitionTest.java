package com.scmspain.bigdata.hadoop.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionDefinitionTest
{
    private  OptionDefinition optionDefinition;

    @Before
    public void setUp() throws Exception
    {
        optionDefinition = new OptionDefinition("test", "t", "test");
    }

    @After
    public void tearDown() throws Exception
    {
        optionDefinition = null;
    }

    @Test(expected=InvalidArgumentException.class)
    public void testShortOptionLongestThanOneChar() throws InvalidArgumentException
    {
        new OptionDefinition("test", "test", "test");
    }

    @Test
    public void testGetLongOption() throws Exception
    {
        assertEquals(
                "Long option should be the one provided",
                "test",
                optionDefinition.getLongOption()
        );
    }

    @Test
    public void testGetShortOption() throws Exception
    {
        assertEquals(
                "Short option should be the one provided",
                "t",
                optionDefinition.getShortOption()
        );
    }

    @Test
    public void testGetHelp() throws Exception
    {
        assertEquals(
                "Help option should be the one provided",
                "test",
                optionDefinition.getHelp()
        );
    }
}