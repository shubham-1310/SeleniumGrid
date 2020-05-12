package com.automation.qa.util;

import org.testng.annotations.BeforeSuite;

public class SeleniumGridStartup {
    @BeforeSuite
    public void startUpGrid(){
        try
        {
            // Just one line and you are done !
            // We have given a command to start cmd
            // /K : Carries out command specified by string
            Runtime.getRuntime().exec(new String[] {"cmd", "/K", " cd C:\\Users\\Shubham Kumar Gupta\\Downloads\\SeleniumGrid-master"});

        }
        catch (Exception e)
        {
            System.out.println("HEY Buddy ! U r Doing Something Wrong ");
            e.printStackTrace();
        }

    }
}
