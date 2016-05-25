package com.scmspain.bigdata.hadoop;

import java.text.SimpleDateFormat;
import java.util.HashMap;

class GenerateDates
{
    private static final String INIT_DATE = "init_date";
    private static final String END_DATE = "end_date";
    private static final String PARTITION_KEY_VALUE = "partition_key_value";
    private static final String PARTITION_RANGE_START = "partition_range_start_";
    private static final String PARTITION_RANGE_END = "partition_range_end_";
    private static final String PARTITION_DAY = "partition_day_";
    private static final String PARTITION_HOUR = "partition_hour_";
    private static final String PARTITION_CURRENT = "current";

    private static final String VALUES_TO_REPLACE = "mm|ss|mi";
    private static final String INIT_DATE_REPLACEMENT = "00";
    private static final String END_DATE_REPLACEMENT = "59";

    private static final Integer HOUR_OFFSET = 3600;

    private Arguments arguments;
    private CalendarInterface time_ago;

    public GenerateDates(Arguments arguments, CalendarInterface calendar)
    {
        this.arguments = arguments;
        time_ago = calendar;
    }

    public HashMap<String, String> getDates()
    {
        SimpleDateFormat dateFormatStart = new SimpleDateFormat();
        dateFormatStart.setTimeZone(arguments.getArgTimezone());

        SimpleDateFormat dateFormatEnd = new SimpleDateFormat();
        dateFormatEnd.setTimeZone(arguments.getArgTimezone());

        SimpleDateFormat dateFormatPartition = new SimpleDateFormat();
        dateFormatPartition.setTimeZone(arguments.getArgTimezone());

        dateFormatStart.applyPattern(arguments.getArgDateFormat().replaceAll(VALUES_TO_REPLACE, INIT_DATE_REPLACEMENT));
        dateFormatEnd.applyPattern(arguments.getArgDateFormat().replaceAll(VALUES_TO_REPLACE, END_DATE_REPLACEMENT));
        dateFormatPartition.applyPattern(arguments.getArgPartitionKeyFormat());

        int staticHours = arguments.getArgStaticHoursToImport();
        int secondsToImport =  (staticHours > 0 ? -(staticHours * HOUR_OFFSET) : arguments.getArgSecondsToImport());

        return getDateRanges(staticHours, secondsToImport, dateFormatStart, dateFormatEnd, dateFormatPartition);
    }

    private HashMap<String, String> getDateRanges(int staticHours, int secondsToImport, SimpleDateFormat dateFormatStart,
                                                  SimpleDateFormat dateFormatEnd, SimpleDateFormat dateFormatPartition)
    {
        HashMap<String, String> dates = new HashMap<String, String>();

        time_ago.addSeconds(secondsToImport);

        String initDate = dateFormatStart.format(time_ago.getTime());
        String endDate = dateFormatEnd.format(time_ago.getTime());
        String partitionFormat = dateFormatPartition.format(time_ago.getTime());

        dates.put(PARTITION_KEY_VALUE, partitionFormat);
        dates.put(INIT_DATE, initDate);

        if (staticHours > 0) {
            for (int i = 0; i < staticHours; i++) {
                dates.put(
                        PARTITION_DAY.concat(String.valueOf(i)),
                        dateFormatPartition.format(time_ago.getTime())
                );
                dates.put(
                        PARTITION_HOUR.concat(String.valueOf(i)),
                        time_ago.getHour()
                );

                dates.put(
                        PARTITION_RANGE_START.concat(String.valueOf(i)),
                        initDate
                );
                dates.put(
                        PARTITION_RANGE_END.concat(String.valueOf(i)),
                        endDate
                );

                time_ago.addSeconds(HOUR_OFFSET);

                initDate = dateFormatStart.format(time_ago.getTime());
                endDate = dateFormatEnd.format(time_ago.getTime());

            }

            dates.put(
                    PARTITION_DAY.concat(PARTITION_CURRENT),
                    dateFormatPartition.format(time_ago.getTime())
            );
            dates.put(
                    PARTITION_HOUR.concat(PARTITION_CURRENT),
                    time_ago.getHour()
            );
            dates.put(
                    PARTITION_RANGE_START.concat(PARTITION_CURRENT),
                    initDate
            );
            dates.put(
                    PARTITION_RANGE_END.concat(PARTITION_CURRENT),
                    endDate
            );
        }
        else time_ago.addSeconds(-secondsToImport);

        dates.put(
                END_DATE,
                dateFormatEnd.format(time_ago.getTime())
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

    public String getPartitionRangeStartKey()
    {
        return PARTITION_RANGE_START;
    }

    public String getPartitionRangeEndKey()
    {
        return PARTITION_RANGE_END;
    }

    public String getPartitionHourKey()
    {
        return PARTITION_HOUR;
    }

    public String getPartitionDayKey()
    {
        return PARTITION_DAY;
    }

    public String getPartitionRangeStartCurrentKey()
    {
        return PARTITION_RANGE_START + PARTITION_CURRENT;
    }

    public String getPartitionRangeEndCurrentKey()
    {
        return PARTITION_RANGE_END + PARTITION_CURRENT;
    }

    public String getPartitionHourCurrentKey() { return PARTITION_HOUR + PARTITION_CURRENT; }

    public String getPartitionDayCurrentKey() { return PARTITION_DAY + PARTITION_CURRENT; }

}
