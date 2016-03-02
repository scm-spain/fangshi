package com.scmspain.bigdata.hadoop;

import java.text.SimpleDateFormat;
import java.util.HashMap;

class GenerateDates
{
    private static final String INIT_DATE = "init_date";
    private static final String END_DATE = "end_date";
    private static final String PARTITION_KEY_VALUE = "partition_key_value";

    private static final String VALUES_TO_REPLACE = "mm|ss|mi";

    private Arguments arguments;
    private CalendarInterface time_ago;

    public GenerateDates(Arguments arguments, CalendarInterface calendar)
    {
        this.arguments = arguments;
        time_ago = calendar;
    }

    public HashMap<String, String> getDates()
    {
        time_ago.addSeconds(arguments.getArgSecondsToImport());

        HashMap<String, String> dates = new HashMap<String, String>();

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.setTimeZone(arguments.getArgTimezone());

        dateFormat.applyPattern(arguments.getArgDateFormat().replaceAll(VALUES_TO_REPLACE, "00"));
        dates.put(
                INIT_DATE,
                dateFormat.format(time_ago.getTime())
        );

        dateFormat.applyPattern(arguments.getArgDateFormat().replaceAll(VALUES_TO_REPLACE, "59"));
        dates.put(
                END_DATE,
                dateFormat.format(time_ago.getTime())
        );

        dateFormat.applyPattern(arguments.getArgPartitionKeyFormat());
        dates.put(
                PARTITION_KEY_VALUE,
                dateFormat.format(time_ago.getTime())
        );

        return dates;
    }

    public String getInitDateKey()
    {
        return INIT_DATE;
    }

    public String getEndDateKey()
    {
        return END_DATE;
    }

    public String getPartitionKeyValueKey()
    {
        return PARTITION_KEY_VALUE;
    }
}
