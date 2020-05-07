package com.pengxiaoming.util;

import com.pengxiaoming.configuration.Constants;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    private static XSSFSheet xssfSheet;
    private static XSSFWorkbook xssfWorkbook;
    private static XSSFCell cell;

    public static void setExcelFile(String Path,String SheetName){
        FileInputStream excelFile;

        try {
            excelFile = new FileInputStream(Path);
            xssfWorkbook = new XSSFWorkbook(excelFile);
            xssfSheet = xssfWorkbook.getSheet(SheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCellData(int RowNum,int ColNum){
        cell = xssfSheet.getRow(RowNum).getCell(ColNum);

        String CellData = cell.getCellType()==XSSFCell.CELL_TYPE_STRING?cell.getStringCellValue()
                +"":String.valueOf(Math.round(cell.getNumericCellValue()));
        return CellData;
    }

    //获取Excel文件最后一行行号
    public static int getLastRowNum(){
        return xssfSheet.getLastRowNum();
    }

    //设定要操作的Excel的文件路劲
    //在读/写Excel文件的时候，需要先设定要操作的Excel文件路劲
    public static void setExcelFile(String path){
        FileInputStream excelFile;

        try {
            excelFile = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(excelFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取指定sheet中的指定单元格方法，此方法只支持拓展名为.xlsx的Excel文件
    public static String getCellData(String sheetName,int rowNum,int colNum){
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        //通过方法参数指定单元格的行号和列号，获取指定的单元格对象
        cell = xssfSheet.getRow(rowNum).getCell(colNum);

        /**如果单元格的内容为字符串类型，则使用getStringCellValue方法获取单元格的内容
         * 如果单元格内容为数字类型，则使用getNumericCellValue方法获取单元格的内容
         */
        String cellData = cell.getCellType() == XSSFCell.CELL_TYPE_STRING?cell.getStringCellValue()+
                "":String.valueOf(Math.round(cell.getNumericCellValue()));
        return cellData;
    }

    //获取指定sheet中的数据总行数
    public static int getRowCount(String sheetName){
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        int number = xssfSheet.getLastRowNum();
        return number;
    }

    //在Excel的指定sheet中，获取第一次包含指定测试用例序号文字的行号
    public static int getFirstRowContainsTestCaseID(String sheetName,String testCaseName,int colNum){
        int i;
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        int rowCount = getRowCount(sheetName);
        for(i = 0;i<rowCount;i++){
            if(getCellData(sheetName,i,colNum).equalsIgnoreCase(testCaseName)){
                //如果包含，则退出循环，并返回包含测试用例号关键字的行号
                break;
            }
        }
        return i;
    }

    //获取指定sheet中某个测试用例步骤的个数
    public static int getTestCaseLastStepRow(String sheetName,String testCaseID,int testCaseStartRowNumber){
        xssfSheet = xssfWorkbook.getSheet(sheetName);
        /**从包含指定测试用例序号的第一行开始逐步遍历，直到某一行不出现指定测试用例序号
         * 此时的遍历次数就是此测试用例步骤的个数*/
        for (int i = testCaseStartRowNumber;i<getRowCount(sheetName)-1;i++){
            if (!testCaseID.equals(getCellData(sheetName,i, Constants.Col_TestCaseID))){
                int number = i;
                return number;
            }
        }
        int number = getLastRowNum()+1;
        return number;
    }
}
