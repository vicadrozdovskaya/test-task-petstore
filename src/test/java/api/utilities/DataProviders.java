package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//testData.xlsx";
        XlsxUtility xlsx = new XlsxUtility(path);
        int rowNumber = xlsx.getRowCount("Sheet1");
        int columnCount = xlsx.getCellCount("Sheet1", 1);

        String[][] data = new String[rowNumber][columnCount];
        for (int i = 1; i <= rowNumber; i++) {
            for (int j = 0; j < columnCount; j++) {
                data[i - 1][j] = xlsx.getCellData("Sheet1", i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//testData.xlsx";
        XlsxUtility xlsx = new XlsxUtility(path);
        int rowNumber = xlsx.getRowCount("Sheet1");

        String[] data = new String[rowNumber];
        for (int i = 1; i <= rowNumber; i++) {
            data[i - 1] = xlsx.getCellData("Sheet1", i, 1);
        }
        return data;
    }
}
