package net.sf.wlogging.ps;

import java.io.PrintStream;

public abstract class WLogPrintStream extends PrintStream {

	protected PrintStream ps = null;
	protected String x = null;
	protected int a;

	public WLogPrintStream(PrintStream arg0, String x) {
		this(arg0, x, 3);
	}

	public abstract void printlnn(String name, Object message);

	public abstract void printn(String name, Object message);

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

}
