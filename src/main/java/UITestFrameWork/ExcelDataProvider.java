package UITestFrameWork;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelDataProvider {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    public  String path;
    public  FileInputStream fis = null;
    public  FileOutputStream fileOut =null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row   =null;
    private XSSFCell cell = null;

    private static XSSFFormulaEvaluator evaluator;


/***************************************************************************
 Constructor : ExcelDataProvider
 Purpose : Initialize the object with path
 Created By : Venkata Siva Kumar
 Created On :
 @param xlFilePath
 *******************************************************************************/
public ExcelDataProvider(String xlFilePath) {

    this.path = xlFilePath;
    try {
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);
        fis.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
    /****************************************************************************
     * Function  Name : getRowCount
     * Purpose : Get the Row count from the sheet Specified
     * Created By :Venkata Siva Kumar B
     * Created On :
     * Comments :
     * @param sheetName
     * Updates :
     * returns : RowCount ( integer )
     * @throws Exception
     ******************************************************************************/
    public int getRowCount(String sheetName)
    {
        int RowCount=0;
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1)
            return RowCount;
        else{
            sheet = workbook.getSheetAt(index);
             RowCount=sheet.getLastRowNum()+1;
            return RowCount;
        }

    }

    /****************************************************************************
     * Function  Name : getCellData
     * Purpose : Get the cell data
     * Input Data :SheetName( String) , Column Nam e(String) , rownumber (int)
     * Created By :
     * Created On :
     * @param sheetName
     * @param colName
     * @param rowNum
     * Comments :
     * Updates :
     * returns :Cell data( String )
     * @throws Exception
     ******************************************************************************/
    // returns the data from a cell
    public String getCellData(String sheetName,String colName,int rowNum){
        try{
            if(rowNum <=0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num=-1;
            if(index==-1)
                return "";

            sheet = workbook.getSheetAt(index);
            row=sheet.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num=i;
            }
            if(col_Num==-1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum-1);
            if(row==null)
                return "";
            cell = row.getCell(col_Num);

            if(cell==null)
                return "";
            //System.out.println(cell.getCellType());
            if(cell.getCellType()==Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){

                String cellText  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal =Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH)+1 + "/" +
                            cellText;

                    //System.out.println(cellText);

                }



                return cellText;
            }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        }
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+colName +" does not exist in xls";
        }
    }

/****************************************************************************
     * Function  Name : getCellData
	 * Purpose : Get the cell data
	 * Input Data :sheetName( String) , Column Number(int) , rownumber (int)
	 * Created By :
     * Created On :
     * @param sheetName
     *  @param colNum
     * @param rowNum
     * Comments :
     * Updates :
      * returns :Cell data( String )
	 * @throws Exception
	 ******************************************************************************/
    // returns the data from a cell
    public String getCellData(String sheetName,int colNum,int rowNum){
        try{
            if(rowNum <=0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if(index==-1)
                return "";


            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum-1);
            if(row==null)
                return "";
            cell = row.getCell(colNum);
            if(cell==null)
                return "";

            if(cell.getCellType()==Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){

                String cellText  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal =Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH)+1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;

                    // System.out.println(cellText);

                }



                return cellText;
            }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
        }
    }
    /****************************************************************************
     * Function  Name : isSheetExist
     * Purpose : find whether sheets exists
     * Input Data :SheetName( String)
     * @param :SheetName
     * Created By :Venkata Siva Kuamr
     * Created On :
     * Comments :
     * Updates :
     * returns : true/false
     * @throws Exception
     ******************************************************************************/


    public boolean isSheetExist(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1){
            index=workbook.getSheetIndex(sheetName.toUpperCase());
            if(index==-1)
                return false;
            else
                return true;
        }
        else
            return true;
    }

    /****************************************************************************
     * Function  Name : getColumnCount
     * Purpose : find whether sheets exists
     * Input Data :SheetName( String)
     * Created By :
     * Created On :
     * @param :sheetName
     * Comments :
     * Updates :
     * returns : returns number of columns in a sheet
     * @throws Exception
     ******************************************************************************/

    public int getColumnCount(String sheetName){
        // check if sheet exists
        if(!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if(row==null)
            return -1;

        return row.getLastCellNum();



    }
    /****************************************************************************
     * Function  Name : excelDataExtrator
     * Purpose : find whether sheets exists
     * Input Data :fileName ,sheetName( String),columName(name of the colum execution flage),noMentionOfSkip (Y or N)
     * @param fileName
     * @param sheetName
     * @param columName
     * @param noMentionOfSkip
     * Created By :
     * Created On :
     *
     * Comments :
     * Updates :
     * returns : retunr hashtable with map objects
     * @throws Exception
     ******************************************************************************/
    public static Object[][] excelDataExtrator(String fileName, String sheetName, String columName,String noMentionOfSkip) throws Exception {

        ExcelDataProvider xls_file = new ExcelDataProvider(fileName);
        int colCount = xls_file.getColumnCount(sheetName);
        int rowCount = xls_file.getRowCount(sheetName);


        int rowReduce =0;

        for(int row=2 ;row<=rowCount;row++){

            if(xls_file.getCellData(sheetName, columName, row).equalsIgnoreCase(noMentionOfSkip)){

                rowReduce++;
            }

        }

        Object[][] data = new Object[rowReduce][1];
        List<String> colNames= new ArrayList<String>();
        for(int col=0;col<colCount;col++) {

            colNames.add(col, xls_file.getCellData(sheetName, col, 1));

        }

        int actualRowCount=0;
        for(int row=2;row<=rowCount;row++){
            if(xls_file.getCellData(sheetName, columName, row).equalsIgnoreCase(noMentionOfSkip))
            {
                Map<String, String> table = new HashMap<String,String>();
                for(int col=0;col<colCount;col++) {
                    table.put(colNames.get(col), xls_file.getCellData(sheetName, col, row));

                }
                data[actualRowCount][0]= table;
                actualRowCount++;
            }

        }
        return data;
    }
}
