package com.demo.util;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ExcelUtils {

	public static String checkExcelImportHeaderFormat(XSSFSheet sheet,List<String> headers) {
		Iterator<Row> rowIterator = sheet.iterator();
		int j=0;
		while (rowIterator.hasNext()) 
		{
			Row row=null;
			row= rowIterator.next();
			if(j==0)
			{	
				System.out.println("checking header");
				int index = -1;
				for (String header : headers) {
					if (!header.equalsIgnoreCase(getColumnByIndex(row, ++index))) {
						return "Imported excel file header "+header+" not in proper format.";
					}
				}
				System.out.println("header is checked properly");
				return "";
			}
			j++;

		} 

		return "Imported excel file empty";
	}

	public static String getColumnByIndex(Row row ,int index)
	{ 
		String value="";

		if(row.getCell(index)!=null){
			Cell cell = row.getCell(index);
			switch (cell.getCellType()) 
			{

			case Cell.CELL_TYPE_NUMERIC:
				value=cell.getNumericCellValue() + "";
				break;
			case Cell.CELL_TYPE_STRING:
				value=cell.getStringCellValue() + "";
				break;
			case Cell.CELL_TYPE_BLANK:
				value= "";
				break;
			}
		}
		return value.trim();
	}

	public static boolean isEmptyRow(Row row){
		boolean isEmptyRow = false;
		for(int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++){
			Cell cell = row.getCell(cellNum);
			if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && org.apache.commons.lang.StringUtils.isNotBlank(cell.toString()))
			{
				isEmptyRow = true;
			}    
		}
		return isEmptyRow;
	}
}
