package com.xero;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class ReusableMethods extends DriverTest{
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;

	public static void enterText(WebElement field,String fieldValue,String fieldName) throws IOException{
		if(field.isDisplayed()){
			field.sendKeys(fieldValue);
			Update_Report("Pass", "enterText", fieldValue+"  is entered in  "+fieldName+" field");
			System.out.println("Pass : "+fieldValue+"  is entered in  "+fieldName+"  field");
		}else{
			Update_Report("Fail", "enterText", fieldName+"  field not displayed");
			System.out.println("Fail :  "+fieldName+"  field not displayed");
		}
	}
	public static void click(WebElement button,String textVal) throws IOException{
		if(button.isDisplayed()){
			button.click();
			Update_Report("successfully", " ", textVal+"  ");
			System.out.println(textVal+"  Done");
		}
	}
	public static void checkfieldValue(WebElement button,String textVal,String value) throws IOException{
		if(button.isDisplayed() && button.getText().contains(value)){
			button.click();
			Update_Report("Pass", "valid data", textVal+" entered ");
			System.out.println(textVal+"  Done");
		}else{
			Update_Report("Fail", "Invalid data", textVal+" entered ");
			System.out.println(textVal+"  Done");
		}
	}
	public static void checkfield(WebElement button,String textVal) throws IOException{
		if(button.isDisplayed()){			
			Update_Report("Pass", " ", textVal+"  ");
			System.out.println(textVal+"  Done");
		}else{
			Update_Report("Fail", " ", textVal+"  ");
			System.out.println(textVal+"  Done");
		}
	}
	
	public static void selectValue(WebElement button,String value,String text) throws IOException{
		if(button.isDisplayed()){
			Select customApp=new Select(button);
			customApp.selectByValue(text);
			Update_Report("Pass", "selectValue", value+" is selected");
			System.out.println("Pass : "+value+" is selected");
		}else{
			Update_Report("Fail", "selectValue", value+"  is not selected");
			System.out.println("Fail : "+value+"  is not selected");
		}
	}
	
	
	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;


		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#E9FF33 WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#E9FF33 WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ result + "</FONT></TD></TR>");

		} 
	}
	public static String[][] readExcel(String dtTablePath,String sheetName) throws IOException{
		File xlFile=new File(dtTablePath);
		FileInputStream xlDoc=new FileInputStream(xlFile);
		HSSFWorkbook wb=new HSSFWorkbook(xlDoc);
		HSSFSheet sheet=wb.getSheet(sheetName);
		//System.out.println(sheetName);
		int iRowCount=sheet.getPhysicalNumberOfRows();
		//System.out.println(iRowCount);
		int iColumnCount=sheet.getRow(0).getLastCellNum();
		String[][] xlData=new String[iRowCount][iColumnCount];
		for(int i=0;i<iRowCount;i++){
			for(int j=0;j<iColumnCount;j++){
				xlData[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println(xlData[i][j]);
			}
		}
		
		return xlData;
	}

}
