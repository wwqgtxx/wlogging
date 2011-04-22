package net.sf.wlogging;

public class Logger {
	private String string;

	private Logger(String string) {
		this.string = string;
	}

	static public Logger getLogger(Class<?> class1) {
		return getLogger(LogName.SIMPLE, class1);
	}

	static public Logger getRootLogger() {
		return new Logger("ROOT LOGGER");
	}

	public void paint(Level l, String s) {
		LogSystem.log.log(l, string, s);
	}

	static public Logger getLogger(LogName ln, Class<?> class1) {
		switch (ln) {
		case FULL:
			return new Logger(class1.getName());
		case SIMPLE:
			return new Logger(class1.getSimpleName());
		default:
			return new Logger(class1.getName());
		}

	}

	static public Logger getLogger(String string) {
		return new Logger(string);
	}

	static public enum LogName {
		FULL, SIMPLE
	}
}
