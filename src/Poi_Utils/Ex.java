package Poi_Utils;

import Employee.Emp;
import JDBC.JDBCUtils.JDBCUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Ex {

    public Ex(String[] title, String filename, String sheetname,String[][] content){
        export(title,filename,sheetname,content);
    }
    public void export( String[] title, String filename, String sheetname,String[][] content){

        HSSFWorkbook wb = GetWorkbook(sheetname,title,content,null);
        filename = "D:\\积分系统\\file\\"+filename+".xls";
        File file = new File(filename);
        try {
            wb.write(file);
           // System.out.println("导出成功！");
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     static HSSFWorkbook GetWorkbook(String sheetName,String[] title,String[][] values,HSSFWorkbook wb){
        if(wb == null)
            wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet(sheetName);

        HSSFRow row = sheet.createRow(0);

        /*HSSFCellStyle  style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.);*/

        HSSFCell cell = null;

        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            //cell.setCellStyle(style);
        }

        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }


        return wb;
    }




}
