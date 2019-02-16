package com.pts.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Utilities {
	
	public static void cleanDrivers(String sDriverName) throws Exception{
		boolean objExists = true;
		Iterator<String> itr = null;
		int pidLength = 0;
		while(objExists) {
			try {
				String pid;
				itr = returnProclist(sDriverName).iterator();
				pidLength = getPidLength();
				while(itr.hasNext()) {
					pid = itr.next().substring(pidLength+1).split("Console")[0].toString().trim();
					System.out.println("PID ->"+pid);
					deleteProcess(pid);
				}
			}catch(Exception e) {
				
			}
			itr = returnProclist(sDriverName).iterator();
			System.out.println("itr ->"+itr.toString());
			if(itr.hasNext()) {
				objExists = true;
			}
			else {
				objExists = false;
			}
		}
	}
	
	protected static void deleteProcess(String pid) {
		try {
			Runtime.getRuntime().exec("TASKKILL /F /PID "+pid);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static int getPidLength() throws Exception {
		int nameLength = 0;
		ArrayList<String> processlist = new ArrayList<String>(getTaskList());
		nameLength = processlist.get(2).toString().split(" ")[0].length();
		return nameLength;
	}
	
	protected static ArrayList<String> getTaskList() throws Exception {
		String line;
		ArrayList<String> processList = new ArrayList<String>();
		Process p = Runtime.getRuntime().exec(System.getenv("windir")+"\\system32\\"+"tasklist.exe");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while((line = br.readLine()) != null) {
			processList.add(line);
		}
		br.close();
		return new ArrayList<String>(processList);
	}
	
	protected static ArrayList<String> returnProclist(String searchCriteria) throws Exception{
		ArrayList<String> driveritr = new ArrayList<String>();
		Iterator<String> itr = null;
		ArrayList<String> processList = new ArrayList<String>(getTaskList());
		
		itr = processList.iterator();
		while(itr.hasNext()) {
			String processitem = itr.next();
			if(processitem.contains(searchCriteria)) {
				driveritr.add(processitem);
			}
		}
		return new ArrayList<String>(driveritr);
	}
	
}
