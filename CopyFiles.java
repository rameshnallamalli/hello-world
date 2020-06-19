package com.orbi.lending;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;

public class CopyFiles {

	public static void main(String[] args) {

		
		String source = "D:/EPIC - ITHALA (K1)/SVN/java/";
		String destination = "D:/JBL/DUMPS/B4 VAPT Chnages/EPIC Files/file_list/part9/";
		
		
	//	String source = "D:/JBL/DUMPS/B4 VAPT Chnages/java_b4vapt/java_b4vapt/";
		//String destination = "D:/JBL/DUMPS/B4 VAPT Chnages/In Process/file_list/part9/";
	
		BufferedReader reader ;
		
		int totFiles =0;

		try {
			reader = new BufferedReader(new FileReader("D:/JBL/DUMPS/filelist.txt"));
			String line  = reader.readLine();
			while (line != null) {
				
				File sourceFileDirectory = new File(source + line);
				File targetFileDirectory = new File(destination + line);
				System.out.println("Source -"+sourceFileDirectory);
				System.out.println("destination -"+destination);
				copy(sourceFileDirectory, targetFileDirectory);		
				line = reader.readLine();
				totFiles++;
				
			}
			reader.close();
			
			System.out.println("Complted "+totFiles);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void copy(File src, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);
			// buffer size 1K
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buf)) > 0) {
				os.write(buf, 0, bytesRead);
			}
			
			System.out.println("copied "+src);
		} finally {
			is.close();
			os.close();
		}
	}
}
