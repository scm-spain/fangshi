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
