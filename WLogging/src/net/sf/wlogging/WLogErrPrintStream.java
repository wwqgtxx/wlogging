package net.sf.wlogging;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class WLogErrPrintStream extends WLogPrintStream {

	// PrintStream ps = null;
	// String x = null;
	// int a;

	public WLogErrPrintStream(PrintStream arg0, String x) {
		this(arg0, x, 3);
	}

	/**
	 * 
	 * @param arg0
	 * @param x
	 * @param a
	 *            3++
	 */
	public WLogErrPrintStream(PrintStream arg0, String x, int a) {
		super(arg0, x, a);

	}

	private void paintErr(Throwable e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String se = (sw.toString());

		ps.println(getString(se));

	}

	public void println(Object o) {
		if (containsAny(o.getClass().getName(), "Exception")) {
			paintErr((Throwable) o);
		} else if (containsAny(o.getClass().getName(), "Error")) {
			paintErr((Throwable) o);
		} else {
			ps.println(o);
		}

	}

	private boolean containsAny(String str, String searchChars) {
		if (str.length() != str.replace(searchChars, "").length()) {
			return true;
		}
		return false;
	}

	@Override
	public void print(Object obj) {
		ps.print(obj);

	}

	@Override
	public void print(boolean obj) {
		ps.print(obj);

	}

	@Override
	public void print(char obj) {
		ps.print(obj);

	}

	@Override
	public void print(char[] obj) {
		ps.print(obj);

	}

	@Override
	public void print(double obj) {
		ps.print(obj);

	}

	@Override
	public void print(float obj) {
		ps.print(obj);

	}

	@Override
	public void print(int obj) {
		ps.print(obj);

	}

	@Override
	public void print(String obj) {
		ps.print(obj);

	}

	@Override
	public void print(long obj) {
		ps.print(obj);

	}

	@Override
	public void println() {
		ps.println();

	}

	@Override
	public void println(boolean x) {
		ps.println(x);

	}

	@Override
	public void println(char x) {
		ps.println(x);

	}

	@Override
	public void println(char[] x) {
		ps.println(x);

	}

	@Override
	public void println(double x) {
		ps.println(x);

	}

	@Override
	public void println(float x) {
		ps.println(x);

	}

	@Override
	public void println(int x) {
		ps.println(x);

	}

	@Override
	public void println(long x) {
		ps.println(x);

	}

	@Override
	public void println(String x) {
		ps.println(x);

	}

}
