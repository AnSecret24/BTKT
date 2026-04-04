package dtm;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataTest {

    // Bước 1: Hàm đọc dữ liệu từ Excel
    @DataProvider(name = "excelData")
    public Object[][] readExcel() throws IOException {
        FileInputStream file = new FileInputStream("shipping_data.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[rowCount - 1][4]; // Trừ 1 dòng tiêu đề

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            data[i-1][0] = row.getCell(0).getNumericCellValue(); // Weight
            data[i-1][1] = row.getCell(1).getStringCellValue();  // Zone
            data[i-1][2] = row.getCell(2).getBooleanCellValue(); // Member
            data[i-1][3] = row.getCell(3).getNumericCellValue(); // Expected
        }
        workbook.close();
        return data;
    }

    // Bước 2: Hàm Test sử dụng dữ liệu từ Excel
    @Test(dataProvider = "excelData")
    public void testShippingExcel(double weight, String zone, boolean member, double expected) {
        ShippingDataTest service = new ShippingDataTest(); // Gọi lại hàm tính ở bài 5.1
        double actual = service.tinhPhiShip(weight, zone, member);
        Assert.assertEquals(actual, expected, 0.001);
    }
}