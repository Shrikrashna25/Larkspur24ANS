package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
public static void main(String[] args) throws IOException {
		
		String baseDir= System.getProperty("user.dir");//CWD,PWD Current Directory,Present Working directory
		System.out.println(baseDir);
		FileInputStream fis = new FileInputStream(baseDir+"/src/main/resources/Config/app.propertiese");
		Properties prop = new Properties();
		prop.load(fis);
	    String value=prop.getProperty("browser_Name");
	    System.out.println(value);
		} 
}
