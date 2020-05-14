package com.pengxiaoming.configuration;

import com.pengxiaoming.util.Log;
import com.pengxiaoming.util.ObjectMap;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import static com.pengxiaoming.util.KeyBoardUtil.pressEnterKey;
import static com.pengxiaoming.util.WaitUtil.sleep;

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
            System.setProperty("webdriver.firefox.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
            driver = new FirefoxDriver();
            Log.info("firefox浏览器实例已经声明");
        }
        else {
            System.setProperty("webdriver.chrome.driver","F:\\BaiduNetdiskDownload\\KeyWordsFrameWork\\driver\\chromedriver.exe");
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName("chrome");
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new ChromeDriver();
            //driver.manage().window().maximize();
            /*options = webdriver.ChromeOptions();
            options.add_argument('--ignore-certificate-errors');
            ddriver = webdriver.Chrome(chrome_options=options);*/
            Log.info("chrome浏览器实例已经声明");
        }
    }

    public static void navigate(String url){
        driver.get(url);
        sleep(3000);
        Log.info("浏览器访问的网址："+url);
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
           driver.findElement(objectMap.getLocator("username")).sendKeys(userName);
            //Log.info("清除用户名输入框的所有内容");
            //driver.findElement(objectMap.getLocator("userName")).sendKeys(userName);
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

            sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void orderManage(){
        try{
            driver.findElement(objectMap.getLocator("orderManage")).click();
            Log.info("点击订单管理");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void leaseList(){
        try{
            driver.findElement(objectMap.getLocator("leaseList")).click();
            Log.info("点击租约列表");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void apartmentName(String apartmentName){
        try{
            driver.findElement(objectMap.getLocator("apartmentname")).click();
            Log.info("点击公寓输入框");
            sleep(3000);
            driver.findElement(objectMap.getLocator("apartmentname")).sendKeys(apartmentName);
            Log.info("点击输入公寓名称");
            sleep(3000);

            pressEnterKey();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Check(String String){
        try{
            driver.findElement(objectMap.getLocator("check")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*public static void GaoJi(String string){
        try {
            driver.findElement(objectMap.getLocator("gaoji")).click();
            Log.info("点击高级按钮");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void AnQuan(String string){
            try {
                driver.findElement(objectMap.getLocator("anquan")).click();
                Log.info("点击安全链接");
                sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
*/
    public static void Assert_String(String assertString){
        Assert.assertTrue(driver.getPageSource().contains(assertString));
        Log.info("断言是否出现"+assertString);
    }


    public static void close_browser(String String){
        driver.quit();
        Log.info("关闭浏览器");
    }
}
