package com.pengxiaoming.testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.pengxiaoming.util.KeyBoardUtil.*;
import static com.pengxiaoming.util.WaitUtil.sleep;
import static com.pengxiaoming.util.WaitUtil.waitWebElement;

/**
 * 完成自动发送带有附件的邮件操作，并进行发送结果关键字的断言
 */
public class TestSendMailWIthAttachment {
    WebDriver driver;
    String baseUrl;

    @Test
    public void testSendMailWithAttachment(){

        driver.get("http://pms.admin.uat.i-fun.tech/page/order-manage/sign-list");

        sleep(3000);

        //定位使用密码登录的链接
        //driver.findElement(By.xpath("//*[@id=\"switchAccountLogin\"]")).click();
        //使用switchTo().frame（）来查询到iframe标签
        //driver.switchTo().frame(0);
        //WaitUtil.sleep(10);
        //定位用户名输入框
        WebElement userName = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        //定位密码输入框
        WebElement passWord = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        //定位到页面登录按钮
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div/div[3]/div/div/span/button"));

        sleep(2000);

        userName.clear();
        userName.sendKeys("admin@sce-re.com");
        passWord.sendKeys("12345678");

        loginButton.click();

        sleep(5000);

        Assert.assertTrue(driver.getPageSource().contains("超级管理员"));
/**
        //调用封装好的显示等待方法，在页面显示出退出链接后，继续执行后续代码逻辑  //*[@id="_mail_component_118_118"]/a
       // waitWebElement(driver,"//a[contains(.,'退出')]");
        //*[@id="_mail_component_113_113"]/a

        //WaitUtil.sleep(3000);
        //定位页面‘写信’链接   //*[@id="_mail_component_24_24"]/span[2]
        WebElement writMailLink = driver.findElement(By.xpath("//*[@id=\"_mail_component_24_24\"]"));
        writMailLink.click();

        //调用封装好的显示等待方法，在页面显示出收件人时 在继续操作后续步骤  //*[@id="_mail_link_38_255"]
        waitWebElement(driver,"//a[contains(.,'收件人')]");

        //定位收件人输入框  /html/body/div[2]/div[1]/div[2]/div[1]/section/header/div[1]/div[1]/div/div[2]
        WebElement recipients = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]" +
                "/section/header/div[1]/div[1]/div/div[2]/div"));

        sleep(3000);
        //定位主题输入框
        WebElement mailSubject = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/section/header/div[2]/div[1]/div/div"));
        //给输入框输入邮件地址
        recipients.sendKeys("p18601632189@126.com");

        mailSubject.sendKeys("selenium测试邮件");
        //调用KeyBoardUtil类中pressTabKey方法执行按键盘Tab键操作
        pressTabKey();
        //调用KeyBoardUtil类中pressTabKey方法执行按键盘Tab键操作
        setAndctrlVClipboardData("这是一封自动化发送的邮件内容");
        sleep(5000);

        //*[@id="_mail_button_183_1026"]/span[2]
        //*[@id="_mail_button_183_1026"]/span[2]
        List<WebElement> buttons = driver.findElements(By.xpath("//*[@id=\"_mail_button_183_1026\"]/span[contains(.,'发送')]"));

        buttons.get(1).click();

        Assert.assertTrue(driver.getPageSource().contains("发送成功"));

        */
    }

    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver","F:\\BaiduNetdiskDownload\\KeyWordsFrameWork\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
