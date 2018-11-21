package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataTake {

    @DataProvider(name = "signupData")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("/Users/jorgevelasquez/IdeaProjects/LearnAutomation/TestData/PHPTravelsTestData.xlsx");

        int sheetSel = 0;  // put here the index of Excel Sheet

        int rows = config.getRowCount(sheetSel);
        int cols = 6;

        Object[][] data = new Object[rows][cols];

        for (int i=0; i<rows; i++)
        {
            for (int j=0; j<cols; j++){

                data[i][j] = config.getData(sheetSel, i, j);
            }

        }

        return data;

    }

}
