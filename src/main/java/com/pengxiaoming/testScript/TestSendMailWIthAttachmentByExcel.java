package com.pengxiaoming.testScript;

import com.pengxiaoming.configuration.Constants;
import com.pengxiaoming.configuration.KeyWordsAction;
import com.pengxiaoming.util.ExcelUtil;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.pengxiaoming.configuration.KeyWordsAction.*;

public class TestSendMailWIthAttachmentByExcel {
    public static Method method[];
    public static String keyword;
    public static String value;
    public static KeyWordsAction keyWordsAction;

    @Test
    public void testSendMailWIthAttachment(){
        keyWordsAction = new KeyWordsAction();
        //使用java的反射机制获取keyWordsAction所有的方法
        method = keyWordsAction.getClass().getMethods();

        //定义关键字文件路径
        String excelFilePath = Constants.Path_ExcelFile;

        // 设定读取Excel文件中登录sheet为操作目标
        ExcelUtil.setExcelFile(excelFilePath,Constants.Sheet_TestSteps);

        for (int iRow = 1;iRow <=ExcelUtil.getLastRowNum();iRow++){
            keyword = ExcelUtil.getCellData(iRow,Constants.Col_KeyWordAction);
            value = ExcelUtil.getCellData(iRow,Constants.Col_ActionValue);

            execute_Actions();
        }
    }

    private static void execute_Actions() {
        try{
            for (int i = 0;i<method.length;i++){
                //通过遍历，判断关键字和keyWordsAction类中的哪一个方法名称一致
                if(method[i].getName().equals(keyword)){
                    //找到keyWordsAction类中的映射方法 后，通过调用invoke方法完成函数调用
                    method[i].invoke(keyWordsAction,value);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
