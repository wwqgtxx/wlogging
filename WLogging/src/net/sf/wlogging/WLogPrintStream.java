package net.sf.wlogging;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class WLogPrintStream extends PrintStream {

	protected PrintStream ps = null;
	protected String x = null;
	protected int a;

	public WLogPrintStream(PrintStream arg0, String x) {
		this(arg0, x, 3);
	}

	/**
	 * 
	 * @param arg0
	 * @param x
	 * @param a
	 *            3++
	 */
	public WLogPrintStream(PrintStream arg0, String x, int a) {
		super(arg0);
		ps = arg0;
		this.x = x;
		this.a = a;
	}

	public abstract void print(Object obj);

	public abstract void print(boolean obj);

	public abstract void print(char obj);

	public abstract void print(char[] obj);

	public abstract void print(double obj);

	public abstract void print(float obj);

	public abstract void print(int obj);

	public abstract void print(String obj);

	public abstract void print(long obj);

	public abstract void println(Object x);

	public abstract void println();

	public abstract void println(boolean x);

	public abstract void println(char x);

	public abstract void println(char[] x);

	public abstract void println(double x);

	public abstract void println(float x);

	public abstract void println(int x);

	public abstract void println(long x);

	public abstract void println(String x);

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
