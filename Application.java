package com.orbi.lending;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * Created on Aug 4, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author pranavam.s
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Application {
	/**
	 * 
	 * @param args
	 *            [0] = Source Directory Ex : D:/Source/ |||| args [1] = Target
	 *            Directory Ex : D:/Project/src/
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		long startTime = new Date().getTime();
		int totalFiles = 0;

		String sourceFileDirectory = "D:/JBL/DUMPS/B4 VAPT Chnages/java_b4vapt/java_b4vapt";
		String targetFileDirectory = "D:/JBL/DUMPS/B4 VAPT Chnages/FileList/";
		String FileNameList  ="";
		
		File testFile = new File(sourceFileDirectory+"/"+"AccEntries.java");
	/*	File arrayFile[] = null;
		Stack stack = new Stack();
		stack.add(testFile);
		while (stack.isEmpty() == false) {
			testFile = (File) stack.pop();
			if (testFile.isFile()) {
				if (testFile.getName().indexOf(".java") >= 0
						&& testFile.getName().indexOf(".bak") == -1) {
					System.out.print("File " + testFile.getAbsoluteFile());
					totalFiles++;*/
					
					moveFile(testFile, targetFileDirectory);
			/*		System.out.println();
				}
			} else if (testFile.isDirectory()) {
				arrayFile = testFile.listFiles();
				for (int i = 0; i < arrayFile.length; i++) {
					stack.add(arrayFile[i]);
				}
			}
		}*/
		long endTime = new Date().getTime();
	
	}

	/**
	 * 
	 * @param fileName
	 * @param sourceFileDirectory
	 * @param targetDirectory
	 * @throws IOException
	 */
	private static void moveFile(File file, String targetDirectory)
			throws IOException {
		Reader reader = null;
		BufferedReader dis = null;
		String strTmp = null;
		boolean st = false;
		File fle = null;
		String sourceFile = file.getAbsolutePath();
		String fileName = file.getName();
		reader = new FileReader(sourceFile);
		dis = new BufferedReader(reader);
		BufferedWriter dos = null;
		String buffer = null;
		st = false;
		int semiColon = 0;
		while (true) {
			strTmp = dis.readLine();
			if (strTmp == null)
				break;
			if (strTmp.trim().indexOf("package") == 0) {
				strTmp = strTmp.trim();
				semiColon = strTmp.indexOf(";");
				if (semiColon >= 0) {
					strTmp = strTmp.substring(0, semiColon);
					strTmp = strTmp.trim();
				}
				strTmp = findPackageName(strTmp);
				System.out.print(" : " + strTmp);
				st = true;
				break;
			}
		}
		String tDir = null;
		tDir = targetDirectory;
		if (st) {
			List lst = getTokens(strTmp, ".");
			fle = null;
			Iterator it = lst.listIterator();
			String tmp;
			while (it.hasNext()) {
				tmp = it.next().toString();
				tDir = tDir.concat(tmp + "/");
				fle = new File(tDir);
				fle.mkdir();
			}
		}
		dis.close();

		dis = new BufferedReader(new FileReader(file));
		dos = new BufferedWriter(new FileWriter(tDir + "/" + fileName));

		while (true) {
			buffer = dis.readLine();
			if (buffer == null) {
				break;
			}
			buffer += "\n";
			dos.write(buffer);
		}
		dis.close();
		dos.close();
	}

	/**
	 * 
	 * @param token
	 * @param sep
	 * @return
	 */
	public static List getTokens(String token, String sep) {
		List myList = new ArrayList();

		StringTokenizer stk = new StringTokenizer(token, sep);

		while (stk.hasMoreTokens()) {
			myList.add(stk.nextToken());
		}
		return myList;
	}

	/**
	 * 
	 * @param packageName
	 * @return
	 */
	private static String findPackageName(String packageName) {
		int index = packageName.indexOf("package") + 8;
		packageName = packageName.substring(index);
		packageName = packageName.substring(0, packageName.length());
		return packageName.trim();
	}
}