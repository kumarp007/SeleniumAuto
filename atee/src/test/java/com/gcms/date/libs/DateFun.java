package com.gcms.date.libs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFun {
	
	public static String getTimeStampFolderName()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy_MM_dd_h_mm");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
}
