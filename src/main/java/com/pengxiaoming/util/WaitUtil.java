package com.pengxiaoming.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
    //用于在执行过程中暂停程序执行的休眠方法
    public static void sleep(long millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //显示等待页面元素出现的封装方法，参数为页面元素的xpath定位字符串
    public static void waitWebElement(WebDriver driver,String xpathExpression){
        WebDriverWait wait = new WebDriverWait(driver,5000);
        //调用ExpectedConditions的presenceOfElementLocated方法判断页面元素是否出现
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
    }

    public static void waitWebElement(WebDriver driver,By by){
        WebDriverWait wait = new WebDriverWait(driver,2000);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
