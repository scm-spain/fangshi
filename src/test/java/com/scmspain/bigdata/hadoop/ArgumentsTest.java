package com.scmspain.bigdata.hadoop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.TimeZone;

public class ArgumentsTest
{
    private static final String ARG_DATE_FORMAT = "date_format";
    private static final String ARG_IMPORT_SECONDS = "-300";
    private static final String ARG_PARTITION_FORMAT = "partition_format";
    private static final String ARG_TIMEZONE = "UTC";
    private static final String ARG_STATIC_HOURS = "2";

    Arguments arguments;

    @Before
    public void setUp() throws Exception
    {
        String[] args = new String[10];
        args[0] = "--import-seconds";
        args[1] = ARG_IMPORT_SECONDS;
        args[2] = "--date-format";
        args[3] = ARG_DATE_FORMAT;
        args[4] = "--partition-format";
        args[5] = ARG_PARTITION_FORMAT;
        args[6] = "--timezone";
        args[7] = ARG_TIMEZONE;
        args[8] = "--static-hours";
        args[9] = ARG_STATIC_HOURS;

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
                "Get seconds to import argument",
                Integer.valueOf(ARG_IMPORT_SECONDS),
                arguments.getArgSecondsToImport()
        );
    }

    @Test
    public void testGetArgDateFormat() throws Exception
    {
        assertEquals(
                "Get the date format provided",
                ARG_DATE_FORMAT,
                arguments.getArgDateFormat()
        );
    }

    @Test
    public void testGetArgPartitionKeyFormat() throws Exception
    {
        assertEquals(
                "Get the partition key format provided",
                ARG_PARTITION_FORMAT,
                arguments.getArgPartitionKeyFormat()
        );
    }

    @Test
    public void testGetArgTimezone() throws Exception
    {
        assertEquals(
                "Get the timezone provided",
                TimeZone.getTimeZone(ARG_TIMEZONE),
                arguments.getArgTimezone()
        );
    }

    @Test
    public void testGetArgStaticHours() throws Exception
    {
        assertEquals(
                "Get the static hours to import",
                Integer.valueOf(ARG_STATIC_HOURS),
                arguments.getArgStaticHoursToImport()
        );
    }
}