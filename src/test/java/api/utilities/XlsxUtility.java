package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XlsxUtility {

    private FileInputStream fileInputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private String path;

    public XlsxUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNumber) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNumber, int columnNumber) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(columnNumber);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }

}
