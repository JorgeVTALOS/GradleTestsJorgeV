package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.*;


public class ReadWriteEXCEL {

    @Test
    public void dataExcelOutput() throws IOException, FileNotFoundException {
        File file = new File("/Users/brayanposada/IdeaProjects/TestingActions/src/Docs/UsersData.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.getCell(1);
        if (cell == null)
            cell = row.createCell(1);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue("Claudia");
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();

    }

}
