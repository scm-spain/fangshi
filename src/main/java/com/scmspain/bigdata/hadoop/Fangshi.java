package com.scmspain.bigdata.hadoop;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;

public class Fangshi
{
    private static final String OOZIE_ACTION_OUTPUT_PROPERTIES = "oozie.action.output.properties";

    public static void main(String[] args) throws Exception
    {
        String oozie_prop = System.getProperty(OOZIE_ACTION_OUTPUT_PROPERTIES);
        Arguments arguments = new Arguments(args);
        GenerateDates generateDates = new GenerateDates(
                arguments,
                new CalendarService().setTimeZone(arguments.getArgTimezone())
        );
        HashMap<String, String> dates = generateDates.getDates();

        if (oozie_prop != null) {
            File propFile = new File(oozie_prop);
            Properties props = new Properties();
            props.setProperty(
                    generateDates.getInitDateKey(),
                    dates.get(generateDates.getInitDateKey())
            );
            props.setProperty(
                    generateDates.getEndDateKey(),
                    dates.get(generateDates.getEndDateKey())
            );
            props.setProperty(
                    generateDates.getPartitionKeyValueKey(),
                    dates.get(generateDates.getPartitionKeyValueKey())
            );

            FileOutputStream os = new FileOutputStream(propFile);
            props.store(os, "");
            os.close();
        } else {
            throw new RuntimeException(OOZIE_ACTION_OUTPUT_PROPERTIES
                    + " System property not defined");
        }
    }
}
