package com.gcms.io.libs;

import java.io.File;

public class IoFun {
	
	public static void createFolder(String folderName)
	{
		File dir = new File(folderName);
		dir.mkdir();
	}
}
