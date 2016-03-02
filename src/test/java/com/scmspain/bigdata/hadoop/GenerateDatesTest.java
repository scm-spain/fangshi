package com.scmspain.bigdata.hadoop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenerateDatesTest
{
    private GenerateDates generateDates;

    @Mock
    private Arguments arguments;

    @Mock
    private CalendarInterface calendar;

    @Before
    public void setUp() throws Exception
    {
        generateDates = new GenerateDates(
                arguments,
                calendar
        );
    }

    @After
    public void tearDown() throws Exception
    {
        arguments = null;
        generateDates = null;
        calendar = null;
    }

    @Test
    public void testGetDatesGivenInputArguments()
    {
        argumentsProvidedByUserAreUsed();
        andDatesAreAdjusted();

        HashMap<String, String> dates = generateDates.getDates();

        assertEquals(
                "Partition key value should be using the right format",
                "20151124",
                dates.get(generateDates.getPartitionKeyValueKey())
        );

        assertEquals(
                "Initial date should be adjusted",
                "2015-11-24 09:00:00",
                dates.get(generateDates.getInitDateKey())
        );

        assertEquals(
                "End date should be adjusted",
                "2015-11-24 09:59:59",
                dates.get(generateDates.getEndDateKey())
        );
    }

    @Test
    public void testGetDatesGivenInputArgumentsWithStaticHours()
    {
        argumentsProvidedByUserAreUsedWithStaticHours();
        andDatesAreAdjusted();

        HashMap<String, String> dates = generateDates.getDates();

        assertEquals(
                "Partition key value should be using the right format",
                "20151124",
                dates.get(generateDates.getPartitionKeyValueKey())
        );

        assertEquals(
                "Initial date should be adjusted",
                "2015-11-24 09:00:00",
                dates.get(generateDates.getInitDateKey())
        );

        assertEquals(
                "End date should be adjusted",
                "2015-11-24 09:59:59",
                dates.get(generateDates.getEndDateKey())
        );

        assertEquals(
                "Static hour should be adjusted",
                "9",
                dates.get(generateDates.getPartitionHourKey().concat("0"))
        );

        assertEquals(
                "Static date range start should be adjusted",
                "2015-11-24 09:00:00",
                dates.get(generateDates.getPartitionRangeStartKey().concat("0"))
        );

        assertEquals(
                "Static date range end should be adjusted",
                "2015-11-24 09:59:59",
                dates.get(generateDates.getPartitionRangeEndKey().concat("0"))
        );
    }

    private void argumentsProvidedByUserAreUsed()
    {
        when(arguments.getArgSecondsToImport()).thenReturn(-3600);
        when(arguments.getArgDateFormat()).thenReturn("yyyy-MM-dd HH:mm:ss");
        when(arguments.getArgPartitionKeyFormat()).thenReturn("yyyyMMdd");
        when(arguments.getArgTimezone()).thenReturn(TimeZone.getTimeZone("UTC"));
    }

    private void andDatesAreAdjusted()
    {
        when(calendar.addSeconds(-3600)).thenReturn(calendar);

        // 1448358532 = Tue, 24 Nov 2015 09:48:52 +0000
        when(calendar.getTime()).thenReturn(new Date(Long.parseLong("1448358532000")));
        when(calendar.getHour()).thenReturn("9");
    }

    private void argumentsProvidedByUserAreUsedWithStaticHours()
    {
        when(arguments.getArgStaticHoursToImport()).thenReturn(1);
        when(arguments.getArgDateFormat()).thenReturn("yyyy-MM-dd HH:mm:ss");
        when(arguments.getArgPartitionKeyFormat()).thenReturn("yyyyMMdd");
        when(arguments.getArgTimezone()).thenReturn(TimeZone.getTimeZone("UTC"));
    }
}