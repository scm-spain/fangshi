package com.scmspain.bigdata.hadoop;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarService implements CalendarInterface
{
    private Calendar calendar;

    public CalendarService()
    {
        calendar = Calendar.getInstance();
    }

    @Override
    public CalendarInterface setTimeZone(TimeZone timeZone)
    {
        calendar.setTimeZone(timeZone);

        return this;
    }

    @Override
    public CalendarInterface addSeconds(Integer seconds)
    {
        calendar.add(Calendar.SECOND, seconds);

        return this;
    }

    @Override
    public Date getTime()
    {
        return calendar.getTime();
    }
}
