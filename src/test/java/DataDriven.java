import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

public class DataDriven {

    // step a) identify Testcase column by scanning entire first row
    // step b) once identified the column, scan entire testcase column to identify the row you need (es. Purchase)
    // step c) after you grab purchase testcase, pull all the data of the row and feed into test


    public ArrayList<String> getData(String testcaseName, String sheetNameInExcel) throws IOException {
        ArrayList<String> arr = new ArrayList<String>();  // I will store the values on the excel in the array and return it
        FileInputStream fis = new FileInputStream("C:\\Users\\dmarinel\\OneDrive - Capgemini\\Documents\\CORSI ONLINE\\ExcelDataDriven\\dataset.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        int sheets = wb.getNumberOfSheets();
        for(int j=0;j<sheets;j++){
            if(wb.getSheetName(j).equalsIgnoreCase(sheetNameInExcel)){
                XSSFSheet sheetData = wb.getSheetAt(j);
                //step a)
                Iterator<Row> rows = sheetData.iterator();  // sheet is collection of rows
                Row firstRow = rows.next();
                Iterator<Cell> cell = firstRow.cellIterator();  // row is a collection of cells
                int k = 0;
                int col = 0;
                while(cell.hasNext()) {   // is next Cell present in the excel sheet?
                    Cell cellValue = cell.next();
                    if(cellValue.getStringCellValue().equalsIgnoreCase("Testcase")){
                        // desidered column

                        col = k;

                    }
                    k++;
                }
                System.out.println(col);

                // step b)
                while(rows.hasNext()){
                    Row r = rows.next();   //scan values to the desired column
                    if(r.getCell(col).getStringCellValue().equalsIgnoreCase(testcaseName)){
                        //match found --> row found
                        Iterator<Cell> cValues = r.cellIterator();
                        while(cValues.hasNext()){
                            Cell c = cValues.next();
                            if(c.getCellType()== CellType.STRING) {
                                arr.add(c.getStringCellValue());
                            }else{
                                arr.add(String.valueOf(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }

        }
        return arr;
    }
}
