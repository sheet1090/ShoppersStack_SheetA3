package com.ShoppersStack.GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtility {
//new wmethod in select 1. se3lectContainsVisisblText ....2.deselectbyContainsVisisbleText()// use part of text
	// getAttribute 
	//JavaUtility javaUtility=new JavaUtility();
	public String readDataFromePropertyFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(FrameWorkConstants.propertyFilePath);
		Properties pro=new Properties();//propertries ic class
		pro.load(fis);;
		String value=pro.getProperty(key);//every time key value get change so we dont want to hardcode so we store into variable
		return value;
		
	}
	
	public String readDataFromeExcelFile(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(FrameWorkConstants.excelFilePath);
		Workbook wb=WorkbookFactory.create(fis);//workbook is interface
		String value=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();//toString () accept all datatype, but getStringcellvalue take only string
		return value;
		
	}
}
