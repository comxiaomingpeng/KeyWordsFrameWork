package com.pengxiaoming.configuration;

import com.pengxiaoming.util.Log;
import com.pengxiaoming.util.ObjectMap;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

public class KeyWordsAction {
    public static WebDriver driver;
    private static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile);

    static {
        DOMConfigurator.configure("log4j.xml");
    }

    public static void open_browser(String browserName){
        if(browserName.equals("ie")){
            System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
            driver = new InternetExplorerDriver();
            Log.info("IE浏览器实例已经声明");
        }
        else if(browserName.equals("firefox")){
            System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
            driver = new FirefoxDriver();
            Log.info("firefox浏览器实例已经声明");
        }
        else {
            System.setProperty("webdriver.chrome.driver","F:\\BaiduNetdiskDownload\\KeyWordsFrameWork\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Log.info("chrome浏览器实例已经声明");
        }
    }

    public static void navigate(String url){
        driver.get(url);
        Log.info("浏览器访问网址"+url);
    }

    public static void switchLogin(String string){
        try {
            driver.findElement(objectMap.getLocator("switch")).click();
            Log.info("定位到密码输入方式登录");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userName(String userName){
        System.out.println("收到的参数值"+userName);
        try {
            driver.findElement(objectMap.getLocator("username")).clear();
            Log.info("清除用户名输入框的所有内容");
            driver.findElement(objectMap.getLocator("userName")).sendKeys(userName);
            Log.info("输入用户名："+userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void passWord(String passWord){
        System.out.println("收到的参数值"+passWord);
        try {
            driver.findElement(objectMap.getLocator("password")).clear();
            Log.info("清除密码输入框的所有内容");
            driver.findElement(objectMap.getLocator("password")).sendKeys(passWord);
            Log.info("输入密码："+passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Login(String string){
        try {
            driver.findElement(objectMap.getLocator("loginButton")).click();
            Log.info("点击登录按钮");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Assert_String(String assertString){
        Assert.assertTrue(driver.getPageSource().contains(assertString));
    }

    public static void close_browser(){
        driver.quit();
    }
}
