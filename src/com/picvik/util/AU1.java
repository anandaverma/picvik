package com.picvik.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.CharBuffer;

public class AU1 {

	/**
	 * @author abbas
	 * @version 1.3 of January, 2005.
	 * @author Abbas K. Sutarwala (with help from Siva) added a test line on
	 *         august 18
	 */
	// By Deekshith on 18 Aug
	/*
	 * Contains some simple and useful Utility functions like the following:<br>
	 * <br> Reads a file into a 2-dimensional String array which is returned<br>
	 * string [] [] file2Array(bufferedreader br, int rowcount, int colcount,
	 * int option, string delimiter)<br> <br> Reads a file into a 2-dimensional
	 * int array which is returned<br> string [] [] file2Array(bufferedreader
	 * br, int rowcount, int colcount, int option, string delimiter)<br> <br>
	 * obtain the line count of a file<br> int getLineCount(bufferedreader
	 * br);<br> Convert an integer character to a 2 character hex string<br>
	 * String strConvertInt2Hex(int intChar)<br> <br> Convert a byte array to a
	 * hex string<br> String convertBytes2Hex(byte [] byteArray)<br> <br> All
	 * functions are static<br>
	 * 
	 * @author Abbas K. Sutarwala.
	 * 
	 * @version 1.0.
	 */

	public static String debugString = "";
	public static final String ALL_SPACES = "                          "
			+ "                                                                "
			+ "                                                                ";

	// static Robot robot;
	static String keyboardBuffer = "";
	public static String reportName = "/report.txt";

	/*
	 * static { try { robot = new Robot();
	 * //System.out.println("robot created"); } }
	 */
	public static void main(String[] args) {
		CharsetEncoder encoder;
		Charset charset;
		try {
			getLogCurrDateTime();

		} catch (Exception ex) {
			System.out.println("exception in main");
			ex.printStackTrace();
		}

	}

	public static int strToInt(String input) {
		if (input == null) {
			return 0;
		}
		try {
			return Integer.parseInt(input);
		} catch(Exception e) {
			return 0;
		}
	}

	public static String getStr(Object input) {
		if (input == null) {
			return "";
		}
		return input.toString();
	}
	public static ArrayList toArrayList(Object[] objects) {
		ArrayList result = new ArrayList();
		for (int i = 0; i < objects.length; i++) {
			result.add(objects[i]);
		}
		return result;
	}

	/**
	 * Skips whitespace and reads the next word (a string of consecutive
	 * non-whitespace characters (up to but excluding the next space, newline,
	 * etc.)
	 * 
	 * @return the read string or null if trying to read beyond the EOF
	 */
	public static String readWord(BufferedReader myInFile) {
		StringBuffer buffer = new StringBuffer(128);
		char ch = ' ';
		int count = 0;
		String s = null;

		try {
			while (myInFile.ready() && Character.isWhitespace(ch)) {
				ch = (char) myInFile.read();
			}
			while (myInFile.ready() && !Character.isWhitespace(ch)) {
				count++;
				buffer.append(ch);
				myInFile.mark(1);
				ch = (char) myInFile.read();
			}
			if (count > 0) {
				myInFile.reset();
				s = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("catch error in /AU1.java @ line 120");
		}

		return s;
	}

	/**
	 * determine if a number is odd
	 * 
	 * @param aNumber
	 * @return true if aNumber is odd; false if it is even
	 */
	public static boolean isOdd(int aNumber) {
		int remainder = aNumber;
		while (remainder > 1) {
			remainder -= 2;
		}
		return (remainder == 1);
	}

	public static String[] textFile2SQLLines(String fileName) {
		System.out.println("entered textFile2SQLLines(" + fileName + ")");
		String[] lines;
		lines = textFile2Lines(fileName);
		String[] newResult = new String[lines.length];
		int j = 0;
		String sql = "";
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			// MyLog.log("sql line:" + line + ":");
			// System.out.println("sql line:" + line + ":");
			if ((line.startsWith("//")) || (line.startsWith("#"))
					|| (line.length() == 0)) {
				// comment line so ignore
				// MyLog.log("ignoring line in textFile2SQLLines");
				// System.out.println("ignoring line in textFile2SQLLines");
				continue;
			}
			sql += lines[i] + " ";
			if (sql.trim().endsWith(";")) { // end of sql
				newResult[j++] = sql;
				// System.out.println("sql:" + sql);
				sql = "";
			}
		}
		String[] sqlStmts = new String[j];
		for (int i = 0; i < j; i++) {
			sqlStmts[i] = newResult[i];
		}

		return sqlStmts;
	}

	/**
	 * Check if the int value is present in the int array;
	 * 
	 * @return the positon of the matching value in the array
	 */
	public int checkValueInArray(int value, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return i;
			}
		}
		return -1; // match not found!
	}

	/**
	 * flush Keyboard buffer; this helps to read keystrokes via a program
	 * 
	 */
	public static void flushKeyboardBuffer() {
		keyboardBuffer = "";
	}

	/**
	 * programmatically simulate a keyboard entry. this is useful for automated
	 * testing which requires user-input.
	 * 
	 * @param entry
	 */
	public static void add2KeyboardBuffer(String entry) {
		keyboardBuffer += entry;
	}

	/**
	 * gets a character first from the keyboard buffer, <br>
	 * if the buffer is empty, reads a character from the keyboard.
	 * 
	 * @return the character obtained as a String
	 */
	public static String getKeyboardCharacter() {
		String character = "";
		if (keyboardBuffer.length() > 0) {
			character = keyboardBuffer.substring(0, 1);
			keyboardBuffer = keyboardBuffer.substring(1);
			// System.out.println(character);
		} else {
			char[] charTemp = { 'S' };
			try {
				byte[] b = new byte[7];
				int i = System.in.read(b);
				charTemp[0] = (char) b[0];
			} catch (IOException ex) {
				System.out.println("catch error in /AU1.java @ line # 223");
			}
			character = new String(charTemp);
		}
		return character;
	}

	/**
	 * read a string from the keyboard
	 * 
	 * @return the string read
	 */
	public static String keyboardInput() {
		byte[] b = new byte[100];
		int bytesRead = 0;
		try {
			bytesRead = System.in.read(b);
		} catch (IOException ex) {
			System.out.println("IOException in read =\n" + ex);
			return "";
		}
		char[] charTemp = new char[bytesRead];
		for (int i = 0; i < (bytesRead); i++) {
			charTemp[i] = (char) b[i];
		}
		String input = new String(charTemp);
		input = input.trim();
		System.out.println("keyboardInput =:" + input + ":");
		return input;
	}

	/**
	 * Convert a hex character (0-9, A-F) to the <br>
	 * corresponding int value from 0 to 15.
	 * 
	 * @param chrChar
	 *            hex character
	 * @return the decimal equivalent of the hex code
	 */
	public static int hex2Int(char chrChar) {
		int intChar = 0;
		switch (chrChar) {
		case '0':
			intChar = 0;
			break;
		case '1':
			intChar = 1;
			break;
		case '2':
			intChar = 2;
			break;
		case '3':
			intChar = 3;
			break;
		case '4':
			intChar = 4;
			break;
		case '5':
			intChar = 5;
			break;
		case '6':
			intChar = 6;
			break;
		case '7':
			intChar = 7;
			break;
		case '8':
			intChar = 8;
			break;
		case '9':
			intChar = 9;
			break;
		case 'A':
			intChar = 10;
			break;
		case 'B':
			intChar = 11;
			break;
		case 'C':
			intChar = 12;
			break;
		case 'D':
			intChar = 13;
			break;
		case 'E':
			intChar = 14;
			break;
		case 'F':
			intChar = 15;
			break;
		default:
			intChar = -1;
			break;
		}
		return intChar;
	}

	/**
	 * convert an integer value into its equivalent hex string
	 * 
	 * @param intChar
	 *            the integer value
	 * @return the hex string
	 * 
	 */
	public static String strConvertInt2Hex(int intChar) {
		String strHex = "";
		if (intChar < 0) {
			intChar = (intChar + 256);
		}
		strHex = Integer.toHexString(intChar);
		if (strHex.length() < 2) {
			strHex = "0" + strHex;
		}
		return strHex;
	}

	/**
	 * Convert a byteArray to a hex string
	 * 
	 * @param byteArray
	 *            the byte array
	 * @return the hexstring corresponding the array
	 */
	public static String convertBytes2Hex(byte[] byteArray) {
		String strHexString = "";
		String strCharHex = "";
		for (int i = 0; i < byteArray.length; i++) {
			int iChar = (int) byteArray[i];
			strCharHex = strConvertInt2Hex(iChar);
			strHexString = strHexString + strCharHex + ", ";
		}
		return strHexString;
	}

	/**
	 * open a BufferedReader
	 * 
	 * @param fileName
	 *            name of the file
	 * @return the BufferedReader opened
	 */
	public static BufferedReader getOKFile(String fileName) {
		BufferedReader inFile = null;
		System.out.println("in getOKFile with fileName=" + fileName);
		try {
			inFile = new BufferedReader(new FileReader(fileName), 1024);
		} catch (FileNotFoundException e) {
			System.err.println("Can't open " + fileName);
			System.err.println("Abandoning!");
			System.exit(1);
		}
		return inFile;
	}

	/**
	 * Compare two files
	 * 
	 * @param file1
	 *            name of the first file
	 * @param file2
	 *            name of the second file
	 * @return true if the two files match<br>
	 *         false if the two files mismatch
	 */
	public static boolean compareFile(String file1, String file2) {
		// System.out.println("in compareFile with " + file1 + ", " + file2);
		boolean resultOK = true;
		BufferedReader file1In = getOKFile(file1);
		BufferedReader file2In = getOKFile(file2);
		String file1Row = "";
		String file2Row = "";
		long row = 0;
		try {
			while (true) {
				file1Row = file1In.readLine();
				file2Row = file2In.readLine();
				if ((file1Row == null) && (file2Row == null)) {
					resultOK = true;
					break;
				}
				if ((file1Row == null) || (file2Row == null)) {
					// System.out.println("one of the 2 files ended earlier");
					resultOK = false;
					break;
				}
				row += 1;
				if (!(file1Row.equals(file2Row))) {
					System.out.println("mismatch" + file1Row);
					System.out.println("        " + file2Row);
					resultOK = false;
					break;
				}
			}
		} catch (IOException ex) {
			System.out.println("catch in AU1.compareFile at line 403");
			ex.printStackTrace();
		}
		return resultOK;
	}

	/**
	 * Provides the string representation of an int [] [] array. <br>
	 * Column separator =":". <br>
	 * Each row is separated by carrige-return line-feed characters.
	 * 
	 * @param myArray
	 *            the 2-dimensional int array to be represented as a String
	 * @return the String representation of the array
	 */
	public static String myToString(int[][] myArray) {
		String result = "";
		for (int row = 0; row < myArray.length; row++) {
			for (int column = 0; column < myArray[row].length; column++) {
				result += myArray[row][column] + ":";
			}
			result += "\r\n";
		}
		System.out.println("result of myToString = \n" + result);
		return result;
	}

	/**
	 * Populates the data from a file into a 2-dimensional int array. Each line
	 * of the file corresponds to a row.<br>
	 * Data separated by commas is written as column values<br>
	 * 
	 * @param textFileName
	 *            the name of the text file
	 * @param maxColumns
	 *            the number of columns in the array
	 * @return the int[][] array containing the values from the text file
	 */
	public static int[][] textFile2ArrayInt(String textFileName, int maxColumns) {
		String[][] arrayString = textFile2ArrayString(textFileName, maxColumns);
		int[][] arrayInt = new int[arrayString.length][maxColumns];
		for (int row = 0; row < arrayString.length; row++) {
			for (int column = 0; column < maxColumns; column++) {
				arrayInt[row][column] = Integer
						.parseInt(arrayString[row][column]);
			}
		}
		return arrayInt;
	}

	/**
	 * display a message on the console
	 * 
	 * @param strMessage
	 *            the message to be displayed
	 * @param strOption
	 *            if ="W", will wait for a keystroke before continuing<br>
	 *            after displaying the message<br>
	 */
	public static void displayMessage(String strMessage, String strOption) {
		System.out.println(strMessage);
		if (strOption.equals("W")) {
			myWait();
		}
	}

	/**
	 * write the message to a report
	 * 
	 * @param Report
	 * @param strMessage
	 *            message to be written
	 * @param intSkipLines
	 *            the number of lines to skip after writing the message
	 */
	public static void reportMessage(BufferedWriter Report, String strMessage,
			int intSkipLines) {
		try {
			Report.write(strMessage);
			for (int i = 0; i < intSkipLines; i++) {
				Report.newLine();
			}
		} catch (IOException ioe) {
			System.out.println("exception in reportMessage");
			ioe.printStackTrace();
		}
	}

	public static void reportMessage(PrintWriter Report, String strMessage,
			int intSkipLines) {
		Report.write(strMessage);
		for (int i = 0; i < intSkipLines; i++) {
			Report.println();
		}
		Report.flush();

	}

	/**
	 * Obtain the count of the number of lines in the file.
	 * 
	 * @param br
	 *            the reader for the file
	 * @return the line count
	 */
	public static int getLineCount(BufferedReader br) throws IOException {
		int lineCount = 0;
		while (br.readLine() != null) {
			lineCount++;
		}
		br.close();
		return lineCount;
	}

	/**
	 * Wait till the user enters a character
	 * 
	 */
	public static void myWait() {
		try {
			System.in.read();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("exception in System.in.read()");
		}
	}

	/**
	 * Get the current date
	 * 
	 * @return the current date
	 */
	public static String getLogCurrDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd-MMM-yyyy hh:mm:ss:SSS a");
		Date currDate = new Date(System.currentTimeMillis());
		// System.out.println("in aks getLogCurr with millisecs:" +
		// System.currentTimeMillis());
		// long millis = System.currentTimeMillis() -
		// ((System.currentTimeMillis() / 1000) * 1000);
		String result = formatter.format(currDate); // + "." + millis;
		return result;
	}

	public static String getLogCurrDate() {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date currDate = new Date(System.currentTimeMillis());
		return formatter.format(currDate);
	}

	public static String getLogCurrDate(String strForMate) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat(strForMate);
		Date currDate = new Date(System.currentTimeMillis());
		return formatter.format(currDate);
	}

	/**
	 * Displays the 1-dimensional array on the system console
	 * 
	 * @param arrayUser
	 *            the String [] array to be displayed
	 * @return void
	 */
	public static void displayArray(String[] arrayUser) {
		int intRowCount = arrayUser.length;
		String strDisplay = "";

		for (int i = 0; i < intRowCount; i++) {
			strDisplay += arrayUser[i] + ", ";
		}
		System.out.println("Array = " + strDisplay);
	}

	/**
	 * Displays the 2-dimensional array on the system console
	 * 
	 * @param arrayUser
	 *            the String [][] array to be displayed
	 * @return void
	 */
	public static void displayArray(String[][] arrayUser) {
		int intRowCount = arrayUser.length;
		int intColCount = arrayUser[0].length;
		String strDisplay = "";

		for (int i = 0; i < intRowCount; i++) {
			for (int j = 0; j < intColCount; j++) {
				strDisplay += arrayUser[i][j] + ", ";
			}
			System.out.println("Elements of Array Row " + i + " = "
					+ strDisplay);
			strDisplay = "";
		}

	}

	/**
	 * gets the list of files in the folder
	 * 
	 * @param folderName
	 *            name of the folder
	 * @return array of file names contained in the folder
	 */
	public static String[] getFilesInFolder(String folderName) {
		File folder = new File(folderName);
		if (folder.isDirectory()) {
			File[] fileNames = folder.listFiles();
			String[] result = new String[fileNames.length];
			for (int i = 0; i < fileNames.length; i++) {
				System.out.println("file " + i + "=" + fileNames[i]);
				result[i] = fileNames[i].getPath();
			}
			return result;
		}
		// aks remove MySwingUtilities.displayMessage(folderName +
		// " is NOT a valid folder!");
		return null;
	}

	/**
	 * Checks if the file exists.
	 * 
	 * @param inputFileName
	 *            file name
	 * @return true if the file exists; false if the file is not present
	 */
	public static boolean fileExists(String inputFileName) {
		InputStream TextIn = null;
		try {
			TextIn = new FileInputStream(inputFileName);
			TextIn.close();
		} catch (IOException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Read a file into a buffer
	 * 
	 * @param inputFileName
	 *            the name of the file
	 * @return the buffer containing the file contents
	 */
	public static byte[] readBytes(String inputFileName) {
		byte[] byteInputBuffer = null;
		int bytesRead = 0;
		InputStream TextIn = null;
		try {
			TextIn = new FileInputStream(inputFileName);
			byteInputBuffer = new byte[TextIn.available()];
			// System.out.println("size of file " + inputFileName + "=" +
			// TextIn.available() + " bytes");
			bytesRead = TextIn.read(byteInputBuffer);
			TextIn.close();
		} catch (IOException ex) {
			System.err.println("Can't open " + inputFileName);
			System.exit(1);
		}
		return byteInputBuffer;
	}

	public static boolean w(String text) {
		return writeText(text);
	}

	public static boolean writeText(String text) {
		return writeText(text, false);
	}

	public static boolean writeText(String text, boolean startNewFile) {
		boolean result = true;
		PrintWriter out;
		System.out.println("in au1.writeText with reportName:" + reportName);
		try {
			out = new PrintWriter(new FileWriter(reportName, !startNewFile));
			out.println(text);
			System.out.println("in au1.writeText after printing");

			out.close();
			System.out.println("in au1.writeText after close");

		} catch (IOException e) {
			System.out.println("cannot open file " + reportName
					+ "in AU1.writeText");
			result = false;
		}
		return result;
	}

	public static boolean writeText(String text, String filePath) {
		boolean result = true;

		try {
			Writer output = null;
			File file = new File(filePath);
			output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.close();
		} catch (IOException ex) {
			System.out.println("cannot open file " + reportName
					+ "in AU1.writeText");
			result = false;
		}

		return result;
	}

	/**
	 * Write a buffer to a file
	 * 
	 * @param outputFileName
	 *            the file name
	 * @param arrayoutput
	 *            the buffer to be written
	 * @return true if written successfully, false otherwise
	 */
	public static boolean writeBytes(String outputFileName, byte[] arrayoutput) {
		boolean result = true;
		OutputStream TextOut = null;
		try {
			TextOut = new FileOutputStream(outputFileName);
			TextOut.write(arrayoutput, 0, arrayoutput.length);
			TextOut.close();
		} catch (IOException ex) {
			result = false;
		}

		return result;
	}

	/**
	 * Remove the trailing spaces from the supplied string
	 * 
	 * @param string
	 *            to be acted upon
	 * @return the supplied string with the trailing spaces removed
	 */
	static public String rightTrim(String string) {
		while (string.endsWith(" ")) {
			string = string.substring(0, string.length() - 1); // remove the
			// last character
		}

		return string;
	}

	/**
	 * read a file into a String
	 * 
	 * @param fileName
	 *            the name of the file
	 * @return the string containing the contents of the file
	 */
	public static String readTextFile(String fileName) {
		byte[] byteInputBuffer = readBytes(fileName);
		System.out.println(byteInputBuffer.toString());

		return new String(byteInputBuffer);
	}

	/*
	 * public static String readTextFileInText(String filePath) { try{
	 * FileInputStream fstream = new FileInputStream(filePath); DataInputStream
	 * in = new DataInputStream(fstream); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(in)); // int sequence_no =
	 * Integer.parseInt(br.readLine()); String seq = br.readLine(); in.close();
	 * return seq; }catch (Exception e){//Catch exception if any
	 * System.err.println("Error: " + e.getMessage()); return null; } }
	 */
	/**
	 * Populates the data from a file into a 2-dimensional String array. Each
	 * line of the file corresponds to a row.<br>
	 * Data separated by commas is written as column values<br>
	 * 
	 * @param textFileName
	 *            the name of the text file
	 * @param maxColumns
	 *            the number of columns in the array
	 * @return the String [] [] array containing the values from the text file
	 */
	public static String[][] textFile2ArrayString(String textFileName,
			int maxColumns) {
		ArrayList data = textFile2ArrayList(textFileName, maxColumns);
		String[][] fileData = new String[data.size()][maxColumns];
		for (int i = 0; i < data.size(); i++) {
			String[] line = (String[]) data.get(i);
			for (int column = 0; column < maxColumns; column++) {
				fileData[i][column] = line[column];
			}

		}
		return fileData;
	}

	/**
	     * 
	     */
	public static String[] textFile2Lines(String textFileName) {
		ArrayList lines = textFile2ArrayList(textFileName);

		String[] result = new String[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			result[i] = (String) lines.get(i);
		}
		System.out.println("RESULT size is :" + result.length);
		return result;
	}

	/**
	 * @param file
	 *            -name, e.g. c:\in.txt
	 * @return the arraylist where each entry is a line from the file
	 */
	public static ArrayList textFile2ArrayList(String textFileName) {
		ArrayList result = new ArrayList();
		BufferedReader fileIn = getOKFile(textFileName);
		try {
			String fileRow = fileIn.readLine();
			while (fileRow != null) {
				// remove trailing spaces
				// fileRow = MyString.rTrim(fileRow);
				fileRow = fileRow.trim();
				// System.out.println("line read by AU1:" + fileRow);
				result.add(fileRow);
				fileRow = fileIn.readLine();
			}
			fileIn.close();
			fileIn = null;
		} catch (IOException ex) {
			System.out.println("Exception in textFile2ArrayList");
			ex.printStackTrace();
			System.exit(1);
		}
		return result;
	}

	public static ArrayList textFile2ArrayList(String textFileName,
			int maxColumns) {
		ArrayList lines = textFile2ArrayList(textFileName);
		ArrayList result = new ArrayList();
		String delimiter = ",";
		for (int i = 0; i < lines.size(); i++) {
			String line = (String) lines.get(i);
			String trimmedLine = line.trim();
			// System.out.println("trimmed line read = :" + trimmedLine);
			if (trimmedLine.equals("")) {
				continue;
			}
			// String[] lineColumn = line.split(delimiter);
			String[] lineColumn = mySplit(line, delimiter, maxColumns);
			// System.out.println("elements in lineColumn = "+
			// lineColumn.length);
			if (lineColumn.length < maxColumns) {
				System.out
						.println("Less elements=" + lineColumn.length
								+ "\n for line = " + line + " for file="
								+ textFileName);
				System.exit(1);
			}

			result.add(lineColumn);
		}

		return result;
	}

	/**
	 * @param line
	 * @param delimiter
	 * @return
	 */
	public static String[] mySplit(String line, String delimiter, int maxTokens) {
		String[] result;
		result = new String[maxTokens];
		String token = line;
		int i = 0;
		while (token.indexOf(delimiter) >= 0 && i < maxTokens) {
			int nMatchPosition = token.indexOf(delimiter);
			result[i++] = token.substring(0, nMatchPosition).trim();
			token = token.substring(nMatchPosition + 1);
		}

		if (i < maxTokens) {
			result[i++] = token.trim();
		}

		return result;
	}

	/**
	 * @param i
	 */
	public static void myWait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void myWait(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
