package com.pengxiaoming.testScript;

import com.pengxiaoming.configuration.Constants;
import com.pengxiaoming.configuration.KeyWordsAction;
import com.pengxiaoming.util.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import static com.pengxiaoming.util.ExcelUtil.*;

public class TestSuiteByExcel {
    public static Method method[];
    public static String keyWord;
    public static String value;
    public static KeyWordsAction keyWordsAction;
    public static int testStep;
    public static int testLastStep;
    public static String testCaseID;
    public static String testCaseRunFlag;

    @Test
    public void testTestSuite(){
        //声明一个关键动作类的实例
        keyWordsAction = new KeyWordsAction();
        //java反射机制获取keyWordsAction类所有方法对象
        method = keyWordsAction.getClass().getMethods();
        String excelFilePath = Constants.Path_ExcelFile;

        //设置读取excel文件中的 登录 sheet为操作目标
        setExcelFile(excelFilePath);

        //读取‘测试用例集合’sheet中的测试用例总数
        int testCaseCount = getRowCount(Constants.Sheet_TestSuite);

        //循环执行所有标记Y的测试用例
        for(int testCaseNO=1;testCaseNO<=testCaseCount;testCaseNO++){
            //读取“测试用例集合”sheet中每行的测试用例序号
            testCaseID = getCellData(Constants.Sheet_TestSuite,testCaseNO,Constants.Col_TestCaseID);
            //读取“测试用例集合”中sheet每行的是否运行的行中的值
            testCaseRunFlag = getCellData(Constants.Sheet_TestSuite,testCaseNO,Constants.Col_RunFlag);
            //如果值为 y 则执行测试用例中的所有步骤
            if (testCaseRunFlag.equalsIgnoreCase("y")){
                Log.startTestCase(testCaseID);
                //在登录sheet中，获取当前要执行测试用例的第一个步骤所在行行号
                testStep = getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps,testCaseID,Constants.Col_TestCaseID);
                //在登录sheet中，获取当前要执行测试用例的最后一个步骤所在行行号
                testLastStep = getTestCaseLastStepRow(Constants.Sheet_TestSteps,testCaseID,testStep);

                //遍历测试用例中所有的测试步骤
                for (;testStep<testLastStep;testStep++){
                    //从登录sheet中读取关键字和操作值，调用execute_Actions方法
                    keyWord = getCellData(Constants.Sheet_TestSteps,testStep,Constants.Col_KeyWordAction);
                    value = getCellData(Constants.Sheet_TestSteps,testStep,Constants.Col_ActionValue);
                    Log.info("在Excel文件中读取到的值："+value);
                    execute_Actions();
                }

                Log.endTestCase(testCaseID);
            }
        }
    }

    private static void execute_Actions() {
        try{
            for (int i = 0;i<method.length;i++){
                //通过遍历，判断关键字和keyWordsAction类中的哪一个方法名称一致
                if(method[i].getName().equals(keyWord)){
                    //找到keyWordsAction类中的映射方法 后，通过调用invoke方法完成函数调用
                    method[i].invoke(keyWordsAction,value);
                    break;
                }
            }
        }catch (Exception e){
            Assert.fail("执行测试用例失败");
        }
    }

    @BeforeClass
    public void BeforeClass(){
        DOMConfigurator.configure("log4j.xml");

    }
}
