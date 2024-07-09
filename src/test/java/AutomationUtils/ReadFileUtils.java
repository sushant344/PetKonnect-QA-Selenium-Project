package AutomationUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileUtils {
	
	static FileInputStream fi;
	static FileOutputStream fo;
	static XSSFWorkbook wo;
	static XSSFSheet sh;
	static XSSFRow row;
	static XSSFCell cell;
	static CellStyle cellstyle;

//	Specify file path and sheet name---
	public static int getRowCount(String file, String sheet) throws IOException {
		fi = new FileInputStream(file);
		wo = new XSSFWorkbook(fi);
		sh = wo.getSheet(sheet);
		int rowCount = sh.getLastRowNum();
		wo.close();
		fi.close();
		return rowCount;
	}
	
	public static int getCellCount(String file, String sheet, int rownum) throws IOException {
		fi = new FileInputStream(file);
		wo = new XSSFWorkbook(fi);
		sh = wo.getSheet(sheet);
		int cellCount = sh.getRow(rownum).getLastCellNum();
		wo.close();
		fi.close();
		return cellCount;
	}
	
	public static String getCellData(String file, String sheet, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(file);
		wo = new XSSFWorkbook(fi);
		sh = wo.getSheet(sheet);
		cell = sh.getRow(rownum).getCell(cellnum);
		String cellData;
		try {
			cellData = cell.toString();
		}catch(Exception e) {
			cellData = "";
		}
		wo.close();
		fi.close();
		return cellData;
	}
	
	public static void setCellData(String file, String sheet, int rownum, int cellnum, String data) throws IOException {
		fi = new FileInputStream(file);
        wo = new XSSFWorkbook(fi);
        sh = wo.getSheet(sheet);
        row = sh.getRow(rownum);
        if (row == null) {
            row = sh.createRow(rownum);
        }
        cell = row.getCell(cellnum);
        if (cell == null) {
            cell = row.createCell(cellnum);
        }
        cell.setCellValue(data);

        cellstyle = wo.createCellStyle();
//        cellstyle.setAlignment(HorizontalAlignment.CENTER);
//        cellstyle.setAlignment(VerticalAlignment.MIDDLE);
        cellstyle.setWrapText(true);
        cell.setCellStyle(cellstyle);

        fo = new FileOutputStream(file);
        wo.write(fo);
        wo.close();
        fi.close();
        fo.close();
	}
	
	public static void fillGreenColor(String file, String sheet, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(file);
		wo = new XSSFWorkbook(fi);
		sh = wo.getSheet(sheet);
		row = sh.getRow(rownum);
		cell = row.getCell(cellnum);
		cellstyle = wo.createCellStyle();
		// Set background color to green
        cellstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Create a font and set its color to white
        Font font = wo.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        cellstyle.setFont(font);
        cell.setCellStyle(cellstyle);
		fo = new FileOutputStream(file);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}
	
	
	public static void fillRedColor(String file, String sheet, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(file);
		wo = new XSSFWorkbook(fi);
		sh = wo.getSheet(sheet);
		row = sh.getRow(rownum);
		cell = row.getCell(cellnum);
		cellstyle = wo.createCellStyle();
		// Set background color to green
        cellstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Create a font and set its color to white
        Font font = wo.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        cellstyle.setFont(font);
        cell.setCellStyle(cellstyle);
		fo = new FileOutputStream(file);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}
	
	
}
