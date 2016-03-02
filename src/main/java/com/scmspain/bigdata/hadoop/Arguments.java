package com.scmspain.bigdata.hadoop;

import com.scmspain.bigdata.hadoop.cli.ArgumentsParser;
import com.scmspain.bigdata.hadoop.cli.InvalidArgumentException;
import com.scmspain.bigdata.hadoop.cli.OptionNotFoundException;
import com.scmspain.bigdata.hadoop.cli.Options;

import java.util.HashMap;
import java.util.TimeZone;

class Arguments
{
    private static final String ARG_SECONDS_TO_IMPORT = "import-seconds";
    private static final String ARG_DATE_FORMAT = "date-format";
    private static final String ARG_PARTITION_KEY_FORMAT = "partition-format";
    private static final String ARG_TIMEZONE = "timezone";
    private static final String ARG_STATIC_HOURS_TO_IMPORT = "static-hours";

    private static final String DEFAULT_SECONDS_TO_IMPORT = "-86400"; // 24 hours
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_PARTITION_KEY_FORMAT = "yyyyMMdd";
    private static final String DEFAULT_TIMEZONE = "Europe/Madrid";
    private static final String DEFAULT_STATIC_HOURS_TO_IMPORT = "0";

    private String[] args;
    private HashMap<String, String> arguments = new HashMap<String, String>();

    public Arguments(String[] args) throws Exception
    {
        this.args = args;

        parseArguments();
    }

    private void parseArguments()
    {
        Options opt = new Options();

        try {
            opt.addOption(ARG_SECONDS_TO_IMPORT, "s", "Seconds to generate a datetime range");
            opt.addOption(ARG_DATE_FORMAT, "d", "Format to print the generated datetime range");
            opt.addOption(ARG_PARTITION_KEY_FORMAT, "f", "Date format to generate the partition key value");
            opt.addOption(ARG_TIMEZONE, "t", "Timezone where the script is run");
            opt.addOption(ARG_STATIC_HOURS_TO_IMPORT, "h", "Number of hours to import before the current time");

            ArgumentsParser cl = new ArgumentsParser(opt).parse(this.args);

            arguments.put(ARG_SECONDS_TO_IMPORT, cl.hasOption("s") ?
                            cl.getOptionValue("s") : DEFAULT_SECONDS_TO_IMPORT
            );
            arguments.put(ARG_DATE_FORMAT, cl.hasOption("d") ?
                            cl.getOptionValue("d") : DEFAULT_DATE_FORMAT
            );
            arguments.put(ARG_PARTITION_KEY_FORMAT, cl.hasOption("f") ?
                            cl.getOptionValue("f") : DEFAULT_PARTITION_KEY_FORMAT
            );
            arguments.put(ARG_TIMEZONE, cl.hasOption("t") ?
                            cl.getOptionValue("t") : DEFAULT_TIMEZONE
            );
            arguments.put(ARG_STATIC_HOURS_TO_IMPORT, cl.hasOption("h") ?
                            cl.getOptionValue("h") : DEFAULT_STATIC_HOURS_TO_IMPORT
            );
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (OptionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public Integer getArgSecondsToImport()
    {
        return Integer.valueOf(arguments.get(ARG_SECONDS_TO_IMPORT));
    }

    public String getArgDateFormat()
    {
        return arguments.get(ARG_DATE_FORMAT);
    }

    public String getArgPartitionKeyFormat()
    {
        return arguments.get(ARG_PARTITION_KEY_FORMAT);
    }

    public TimeZone getArgTimezone()
    {
        return TimeZone.getTimeZone(arguments.get(ARG_TIMEZONE));
    }

    public Integer getArgStaticHoursToImport() { return Integer.valueOf(arguments.get(ARG_STATIC_HOURS_TO_IMPORT)); }
}