package com.picvik.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;

public class Reporter {

	/**
	 * 
	 * @author AbbasKS
	 */
	int messageCount;
	private PrintWriter writer;
	private static Hashtable reporters = new Hashtable();
	private int reportSuffix;
	private String reportPrefix = "";
	private String reportType;

	public Reporter(String reportType) {
		messageCount = 0;
		reportSuffix = 1;
		this.reportType = reportType;
		System.out.println("report name:" + reportPrefix + reportType
				+ reportSuffix + ".txt" );
		writer = getNewWriter(reportPrefix + reportType
				+ reportSuffix + ".txt");
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public PrintWriter getNewWriter(String fileName) {
		PrintWriter result = null;
		try {
			result = new PrintWriter(fileName);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("exception in Reporter constructor");
		}
		return result;
	}

	public void print(String message) {
		print(message, true);
	}

	public void print(String message, boolean replaceCRLF) {
		if (messageCount == 0) {
			AU1.reportMessage(writer, // RuntimeSettings.VERSION_ID +
					"  Report started at: " + AU1.getLogCurrDateTime(), 2);
		}
		messageCount++;
		String prefix = " ";
		if (this.reportType.equals("log_report")) {
			prefix = "@: " + AU1.getLogCurrDateTime().substring(12) + " ";
		} else if ((this.reportType.indexOf("log") >= 0)
				|| (this.reportType.indexOf("catch") >= 0)
				|| (this.reportType.contains("io"))) {
			prefix = "@: " + AU1.getLogCurrDateTime().substring(12)
					+ " Thread:" + Thread.currentThread().getName() + " ";
		}
		if (replaceCRLF) {
			message = message.replace("\r", "\\r");
			message = message.replace("\n", "\\n");
		}
		// String wrappedText = myWrapText(prefix + message);
		String reportMessage = "";
		if ((this.reportType.indexOf("server") >= 0)
				|| (this.reportType.startsWith("IVTS"))) {
			reportMessage = message + " " + prefix;
		} else {
			reportMessage = prefix + message;
		}
		AU1.reportMessage(writer, reportMessage, 1);

		// AU1.reportMessage(writer, wrappedText, 1);
		writer.flush();
	}

	public static String myWrapText(String text) {
		// wraps text so each line is a max of 80 characters
		String wrappedText = "";
		while ((text.length() > 78)
				&& ((text.indexOf("\n") < 0) || (text.indexOf("\n") > 78))) {
			wrappedText += text.substring(0, 78) + "-\r\n";
			text = "    " + text.substring(78);
		}
		wrappedText += text;
		return wrappedText;

	}

	public static Reporter get(String reportType) {
		Reporter result = (Reporter) reporters.get(reportType);
		if (result == null) {
			result = new Reporter(reportType);
			reporters.put(reportType, result);
			// System.out.println("reporters size:" + reporters.size());
		}
		return result;
	}
}
