package com.scmspain.bigdata.hadoop.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArgumentsParserTest
{
    ArgumentsParser parser;

    @Before
    public void setUp() throws Exception
    {
        Options options = new Options();
        options.addOption("long-option", "l", "Testing help");
        options.addOption("short-option", "s", "Testing help");

        parser = new ArgumentsParser(options);
    }

    @After
    public void tearDown() throws Exception
    {
        parser = null;
    }

    @Test
    public void testParseArgumentsWhenLongAndShortOptionsAreProvided() throws OptionNotFoundException
    {
        String[] arguments = new String[4];
        arguments[0] = "--long-option";
        arguments[1] = "test long option";
        arguments[2] = "-s";
        arguments[3] = "test short option";

        parser = parser.parse(arguments);

        assertEquals(
                "Long options are parsed",
                "test long option",
                parser.getOptionValue("long-option")
        );
    }

    @Test
    public void testParseArgumentsWhenNotAllOptionsAreProvided() throws OptionNotFoundException
    {
        String[] arguments = new String[2];
        arguments[0] = "--long-option";
        arguments[1] = "test long option";

        parser = parser.parse(arguments);

        assertNull(
                "Short option are not provided so should be null",
                parser.getOptionValue("short-option")
        );
    }

    @Test
    public void testParseArgumentsWhenNoValueForOptionAreProvided() throws OptionNotFoundException
    {
        String[] arguments = new String[3];
        arguments[0] = "--long-option";
        arguments[1] = "-s";
        arguments[2] = "test short option";

        parser = parser.parse(arguments);

        assertEquals(
                "Long options are parsed",
                "",
                parser.getOptionValue("long-option")
        );

    }
}