package net.sf.wlogging.appender;

import java.io.PrintStream;

public abstract class Appender {
	public abstract PrintStream getOut();

	public abstract PrintStream getErr();

	public static Appender getAppender() {
		throw new UnsupportedOperationException(Appender.class.getName()
				+ "is't a full class");
	}
}
