package com.pengxiaoming.util;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectMap {
    Properties properties;

    public ObjectMap(String propFile){
        properties = new Properties();
        try {
            FileInputStream fs = new FileInputStream(propFile);
            properties.load(fs);
            fs.close();
        } catch (IOException e) {
            System.out.println("读取文件出错");
            e.printStackTrace();
        }
    }

    /**
     * 1、根据参数ElementNameInpropFile，从属性文件中读取对应的配置对象
     * 2、将配置对象中的定位类型存到locatorType变量 ，将定位表达式的值存入locatorValue变量中
     * 3、根据locatorType变量值与locatorValue变量值，验证是否赋值正确
     * @param ElementNameInpropFile
     * @return
     */
    public By getLocator(String ElementNameInpropFile) throws Exception {
        //根据ElementNameInpropFile 从属性配置文件读取对应的配置对象
        String locator = properties.getProperty(ElementNameInpropFile);

        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];

        locatorValue = new String(locatorValue.getBytes("ISO-8859-1"),"UTF-8");

        System.out.println("获取的定位类型：" + locatorType + "\t 获取的定位表达式：" + locatorValue);

        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if (locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if ((locatorType.toLowerCase().equals("classname"))||(locatorValue.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if ((locatorType.toLowerCase().equals("tagname"))||(locatorValue.toLowerCase().equals("tag")))
            return By.tagName(locatorValue);
        else if ((locatorType.toLowerCase().equals("linktext"))||(locatorValue.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if (locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if ((locatorType.toLowerCase().equals("cssselector"))||(locatorValue.toLowerCase().equals("css")))
            return By.className(locatorValue);
        else if (locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else {
            throw new Exception("输入的locator type 未能在程序中找到：" + locatorType);
        }
    }
}
