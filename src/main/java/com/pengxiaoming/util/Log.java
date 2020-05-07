package com.pengxiaoming.util;

import org.apache.log4j.Logger;

public class Log {
    private static Logger log  = Logger.getLogger(Log.class.getName());
    public static void startTestCase(String testCaseName){
        log.info("-------------------"+testCaseName+"开始执行--------------------------");
    }
    public static void endTestCase(String testCaseName){
        log.info("-------------------"+testCaseName+"结束执行--------------------------");
    }

    public  static void info(String message){
        log.info(message);
    }

    public static void error(String message){
        log.error(message);
    }

    public static void debug(String message){
        log.debug(message);
    }
}
