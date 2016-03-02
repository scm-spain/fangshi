package com.scmspain.bigdata.hadoop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentsTest
{
    private static final String ARG_VALUE = "test";
    Arguments arguments;

    @Before
    public void setUp() throws Exception
    {
        String[] args = new String[8];
        args[0] = "--import-seconds";
        args[1] = "1";
        args[2] = "--date-format";
        args[3] = ARG_VALUE;
        args[4] = "--partition-format";
        args[5] = ARG_VALUE;
        args[6] = "--timezone";
        args[7] = ARG_VALUE;

        arguments = new Arguments(args);
    }

    @After
    public void tearDown() throws Exception
    {
        arguments = null;
    }

    @Test
    public void testGetArgSecondsToImport() throws Exception
    {
        assertEquals(
                "Get secods to import argument",
                Integer.valueOf("1"),
                arguments.getArgSecondsToImport()
        );
    }

    @Test
    public void testGetArgDateFormat() throws Exception
    {
        assertEquals(
                "Get the date format provided",
                ARG_VALUE,
                arguments.getArgDateFormat()
        );
    }

    @Test
    public void testGetArgPartitionKeyFormat() throws Exception
    {
        assertEquals(
                "Get the partition key format provided",
                ARG_VALUE,
                arguments.getArgPartitionKeyFormat()
        );
    }

    @Test
    public void testGetArgTimezone() throws Exception
    {
        assertEquals(
                "Get the timezone provided",
                ARG_VALUE,
                arguments.getArgTimezone()
        );
    }
}