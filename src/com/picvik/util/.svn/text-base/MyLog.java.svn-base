package com.picvik.util;

import java.io.PrintWriter;
import java.util.ArrayList;

public class MyLog {

	public static PrintWriter bw = null;
	public static PrintWriter testWriter = null;
	public static PrintWriter rp = null;
	public static PrintWriter messageWriter = null;
	static long startTime = System.currentTimeMillis();
	private static int nMessagesPrinted = 0;
	private static int nReportPrinted = 0;
	private static String myReportName = "/my_report";
	private static String reportName = "/my_log1";
	public static PrintWriter printer = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void setReportName(String newReportName) {
		myReportName = newReportName;
		nReportPrinted = 0;
	}

	public static void resetReportName() {
		myReportName = "/my_report";
		nReportPrinted = 0;
	}

	public static void log_and_print(String message) {
		System.out.println(message);
		log(message);
	}

	public static void logReport(String message) {
		myWrite("log_report", message);
	}

	public static void log(String message) {
		myWrite("log", message);
	}

	public static void log(String reportName, String message) {
		myWrite(reportName, message);
	}

	public static void myIO(String message) {
		// myWrite("catch", message);
		myWrite("Io", message);
	}

	public static void serverIO(String message) {
		// myWrite("catch", message);
		myWrite("serverIo", message);
	}

	public static void myError(String message) {
		myWrite("catch", message);
		log(message);
	}

	public static void warning(String message) {
		System.out.println(message);
		myWrite("warning", message);
	}

	public static void serverCatch(String programName, int line_number,
			Throwable exc) {
		myCatch("serverCatch", programName, line_number, exc);
	}

	public static void myCatch(String programName, int line_number,
			Throwable exc) {
		myCatch("catch", programName, line_number, exc);
	}

	public static void myCatch(String reportType, String programName,
			int line_number, Throwable exc) {

		String message = " entered catch in " + programName + " @ line # "
				+ line_number;
		myWrite(reportType, message);
		Reporter reporter = Reporter.get("catch");
		PrintWriter writer = reporter.getWriter();
		exc.printStackTrace(writer);
		writer.flush();
		exc.printStackTrace();
	}

	public static void myWrite(String reportType, String message) {
		reportType = reportType.toLowerCase();
		Reporter reporter = Reporter.get(reportType);
		reporter.print(message, !(reportType.startsWith("report")));
	}

	public static void reportTestResult(String message) {
		myWrite("reportTestResult", message);
	}

	public static void testLog(String message) {
		myWrite("test_log", message);
		String messageTime = " @: " + AU1.getLogCurrDateTime().substring(12)
				+ " Thread:" + Thread.currentThread().getName() + " ";
		System.out.println(messageTime + message);
	}

	public static void report(PrintWriter out, String text) {
		AU1.reportMessage(out, text, 1);
	}

	public static void report(String text) {
		myWrite("report", text);
	}

	public static void printLine(String text, PrintWriter out) {
		String textPrint = "";
		while ((text.length() > 78)
				&& ((text.indexOf("\n") < 0) || (text.indexOf("\n") > 78))) {
			textPrint += text.substring(0, 78) + "\r\n";
			text = "    " + text.substring(78);
		}
		textPrint += text;
		AU1.reportMessage(out, textPrint, 1);
		out.flush();
		return;
	}

	public static void myReport(String text) {
		// System.out.println("entered myReport with nReportPrinted:" +
		// nReportPrinted);
		myWrite("report", text);
	}

	public static void close(String reportType) {
		Reporter reporter = Reporter.get(reportType);
		PrintWriter writer = reporter.getWriter();
		writer.close();
	}

	public void close() {
	}

	public static void myConn(String string, String string0, String string1,
			int i) {
		return;
	}

	public static void myConn2(String string, String string0, String string1,
			int i) {
		return;
	}
}
