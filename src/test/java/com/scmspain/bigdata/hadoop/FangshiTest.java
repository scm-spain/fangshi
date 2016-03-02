package com.scmspain.bigdata.hadoop;

import org.junit.Before;
import org.junit.Test;

public class FangshiTest
{
    private Fangshi fangshi;

    @Before
    public void setUp()
    {
        fangshi = new Fangshi();
    }

    @Test
    public void testRun()
    {
        String[] args = new String[2];
        args[0] = "-1";
        args[1] = "YYYY-MM-dd hh:mm:ss";

        try {
            fangshi.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}