package com.pengxiaoming.configuration;

/**
 * 用来存储测试框架中用到的格子参数配置，如：数据文件，sheet名称和sheet中使用的列号
 *
 */
public class Constants {
    public static final String Path_ExcelFile = System.getProperty("user.dir") + "\\src\\main\\java\\com\\pengxiaoming\\data\\关键字驱动测试用例.xlsx";
    public static final String Path_ConfigurationFile = System.getProperty("user.dir") + "\\objectMap.properties";

    public static final int Col_TestCaseID = 0;
    public static final int Col_KeyWordAction = 3;
    public static final int Col_ActionValue = 4;
    public static final int Col_RunFlag = 2;
    public static final String Sheet_TestSteps = "登录";
    public static final String Sheet_TestSuite = "测试用例集合";
}
