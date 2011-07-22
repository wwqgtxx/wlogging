package net.sf.wlogging.appender;

import java.io.PrintStream;

public class SysOutAppender extends Appender {

	private SysOutAppender() {
	}

	private static SysOutAppender appender = new SysOutAppender();

	@Override
	public PrintStream getOut() {
		return System.out;
	}

	@Override
	public PrintStream getErr() {
		return System.err;
	}

	public static Appender getAppender() {
		return appender;
	}

}
