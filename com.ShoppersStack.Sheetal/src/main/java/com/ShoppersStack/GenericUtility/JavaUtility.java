package com.ShoppersStack.GenericUtility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class JavaUtility {

	public int generateRandomNumber()
	{
		Random r=new Random();
		int num=r.nextInt(1000);//give lilmit as 1000 to print random number below 1000
		return num;
	}
	public String getLocalDate()
	{
		String date=LocalDate.now().toString().replace("_","");
		return date;
		
	}
	public String getLocalDateTime()
	{
		String dateTime=LocalDateTime.now().toString().replace("_","").replace(":","").replace(".","");
		return dateTime;
	}
}
