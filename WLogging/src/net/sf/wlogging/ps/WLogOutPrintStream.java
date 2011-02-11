package net.sf.wlogging.ps;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WLogOutPrintStream extends WLogAbstractPrintStream {

	// protected PrintStream ps = null;

	// protected String x = null;
	// protected int a;

	public WLogOutPrintStream(PrintStream arg0, String x) {
		this(arg0, x, 3);
	}

	/**
	 * 
	 * @param arg0
	 * @param x
	 * @param a
	 *            3++
	 */
	public WLogOutPrintStream(PrintStream arg0, String x, int a) {
		super(arg0, x, a);

	}

	public void print(Object obj) {
		ps.print(getString(obj));
	}

	public void print(boolean obj) {
		ps.print(getString(obj));
	}

	public void print(char obj) {
		ps.print(getString(obj));
	}

	public void print(char[] obj) {
		ps.print(getString(obj));
	}

	public void print(double obj) {
		ps.print(getString(obj));
	}

	public void print(float obj) {
		ps.print(getString(obj));
	}

	public void print(int obj) {
		ps.print(getString(obj));
	}

	public void print(String obj) {
		ps.print(getString(obj));
	}

	public void print(long obj) {
		ps.print(getString(obj));
	}

	public void println(Object x) {
		ps.println(getString(x));
	}

	public void println() {
		ps.println();
	}

	public void println(boolean x) {
		ps.println(getString(x));
	}

	public void println(char x) {
		ps.println(getString(x));
	}

	public void println(char[] x) {
		ps.println(getString(x));
	}

	public void println(double x) {
		ps.println(getString(x));
	}

	public void println(float x) {
		ps.println(getString(x));
	}

	public void println(int x) {
		ps.println(getString(x));
	}

	public void println(long x) {
		ps.println(getString(x));
	}

	public void println(String x) {
		ps.println(getString(x));
	}

	protected String getString(Object obj) {
		return getTime() + x + "{" + getTraceInfo() + "}" + ": " + obj;
	}

	protected String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	protected String getTraceInfo() {
		StringBuffer sb = new StringBuffer();

		StackTraceElement[] stacks = new Throwable().getStackTrace();

		// for (int i = 0; i < stacks.length; i++) {
		sb.append(stacks[a].getClassName()).append(".")
				.append(stacks[a].getMethodName()).append("(")
				.append(stacks[a].getLineNumber()).append(")");

		// }

		return sb.toString();
	}

}
