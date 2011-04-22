package net.sf.wlogging.ps;

import java.io.PrintStream;

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

	@Override
	public void print(Object obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(boolean obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(char obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(char[] obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(double obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(float obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(int obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(String obj) {
		ps.print(getString(obj));
	}

	@Override
	public void print(long obj) {
		ps.print(getString(obj));
	}

	@Override
	public void println(Object x) {
		ps.println(getString(x));
	}

	@Override
	public void println() {
		ps.println();
	}

	@Override
	public void println(boolean x) {
		ps.println(getString(x));
	}

	@Override
	public void println(char x) {
		ps.println(getString(x));
	}

	@Override
	public void println(char[] x) {
		ps.println(getString(x));
	}

	@Override
	public void println(double x) {
		ps.println(getString(x));
	}

	@Override
	public void println(float x) {
		ps.println(getString(x));
	}

	@Override
	public void println(int x) {
		ps.println(getString(x));
	}

	@Override
	public void println(long x) {
		ps.println(getString(x));
	}

	@Override
	public void println(String x) {
		ps.println(getString(x));
	}

	@Override
	public void printlnn(String name, Object message) {
		ps.println(getString(name, message));
	}

	@Override
	public void printn(String name, Object message) {
		ps.print(getString(name, message));
	}

}
