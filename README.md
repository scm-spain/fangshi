# Fangshi
Generate the date string to import data incrementally when running oozie workflows

## Example
````
    <action name="get-date">
        <java>
            <job-tracker>${jobTracker}</job-tracker>
            <name-node>${nameNode}</name-node>
            <configuration>
                <property>
                    <name>mapred.queue.name</name>
                    <value>default</value>
                </property>
            </configuration>
            <main-class>com.scmspain.bigdata.hadoop.Fangshi</main-class>
            <arg>--import-seconds</arg>
            <arg>${secondsToImport}</arg>
            <arg>--date-format</arg>
            <arg>${dateFormat}</arg>
            <capture-output/>
        </java>
        <ok to="end"/>
        <error to="error"/>
    </action>
````

## Arguments
* s (import-seconds): the number of seconds / offset to import from (for example, "-3600" would import the previous hour). Default is "-86400" (previous day)
* d (date-format): format in which date strings will be generated. Default is "yyyy-MM-dd HH:mm:ss"
* f (partition-format): format in which partition strings will be generated. Default is "yyyyMMdd"
* t (timezone): specify the timezone used when dates are being calculated. Default is "Europe/Madrid"
* h (static-hours): used specifically when we want to generate static partitioning string values. Default value is "0" (no static partitioning).

## Example for static partitioning
Specifying h argument (static-hours) will put static partitioning string values in the following format:
* date_hour_X: this string contains the ${partitionHour} value for the X hour
* date_range_start_X: this string contains the partition range start string for the X hour
* date_range_end_X: this string contains the partition range end string for the X hour
* date_hour_current/date_range_start_current/date_range_end_current: they both contain the same values that their _X relatives but for the current hour.

For example, calling the java class on 12:23 AM with h = 2 (last two hours to import) will generate the following values:
* 0
  * date_hour_0 -> 10
  * date_range_start_0 -> 2016-02-18 10:00:00
  * date_range_end_0 -> 2016-02-18 10:59:59
* 1
  * date_hour_1 -> 11
  * date_range_start_1 -> 2016-02-18 11:00:00
  * date_range_end_1 -> 2016-02-18 11:59:59
* current (h + 1)
  * date_hour_current -> 12
  * date_range_start_current -> 2016-02-18 12:00:00
  * date_range_end_current -> 2016-02-18 12:59:59

#### How to call the java class
Using short arguments
````
java -jar dates.jar -s -3600 -d "yyyy-MM-dd" -f "MMddyyyy" -t "UTC" -h 2
````

Using long arguments
````
java -jar dates.jar --import-seconds -3600 --date-format "yyyy-MM-dd" --partition-format "MMddyyyy" --timezone "UTC" --static-hours 2
````
