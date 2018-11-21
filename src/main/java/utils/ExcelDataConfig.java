package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class ExcelDataConfig {

    XSSFWorkbook wb;
    XSSFSheet sheetSelected;

    //Constructor Creates an Object Excel Kind  (and reads it from an especific Location)
    public ExcelDataConfig(String excelPath)
    {

        try {
            File src = new File(excelPath);

            FileInputStream fis = new FileInputStream(src);

            wb = new XSSFWorkbook(fis);

        }

        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    //Method Obtains data from an Specific Cell in an Excel File
    public String getData(int sheetNumber, int row, int column)
    {
        sheetSelected = wb.getSheetAt(sheetNumber);

        String data = sheetSelected.getRow(row).getCell(column).getStringCellValue();

        return data;
    }

    //Method Obtains # of rows used in a Sheet
    public int getRowCount(int sheetIndex)
    {
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();

        row = row + 1;

        return row;
    }

}
