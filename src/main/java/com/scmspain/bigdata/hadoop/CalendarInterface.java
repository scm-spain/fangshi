package com.scmspain.bigdata.hadoop;

import java.util.Date;
import java.util.TimeZone;

public interface CalendarInterface
{
    CalendarInterface setTimeZone(TimeZone timeZone);

    CalendarInterface addSeconds(Integer seconds);

    Date getTime();
}
