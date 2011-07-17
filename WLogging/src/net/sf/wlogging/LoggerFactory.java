package net.sf.wlogging;

public abstract class LoggerFactory {
	private static class OnlyShowNameLoggerFactory extends LoggerFactory {
		private OnlyShowNameLoggerFactory() {
		}

		public Logger getLogger(LogName ln, Class<?> class1) {
			switch (ln) {
			case FULL:
				return new Logger(class1.getName());
			case SIMPLE:
				return new Logger(class1.getSimpleName());
			default:
				return new Logger(class1.getName());
			}

		}

		@SuppressWarnings("unused")
		public Logger getLogger(String string) {
			return new Logger(string);
		}

		@SuppressWarnings("unused")
		public Logger getSimpleNameLogger(Class<?> class1) {
			return getLogger(LogName.SIMPLE, class1);
		}

		@SuppressWarnings("unused")
		public Logger getSimpleNameLogger(Object obj) {
			return getLogger(LogName.SIMPLE, obj.getClass());
		}

		@SuppressWarnings("unused")
		public Logger getFullNameLogger(Class<?> class1) {
			return getLogger(LogName.FULL, class1);
		}

		@SuppressWarnings("unused")
		public Logger getFullNameLogger(Object obj) {
			return getLogger(LogName.FULL, obj.getClass());
		}

		@SuppressWarnings("unused")
		public Logger getRootLogger() {
			return new Logger("ROOT LOGGER");
		}

	}

	public static class ShowAllMessageLoggerFactory extends LoggerFactory {

		public Logger getLogger() {
			return new Logger() {
				public void paint(Level l, Object o) {
					LogSystem.log.log(l, o);
				}
			};
		}
	}

	public static OnlyShowNameLoggerFactory Only_SHOW_NAME_FACTORY = new OnlyShowNameLoggerFactory();
	public static ShowAllMessageLoggerFactory SHOW_All_MESSAGE_FACTORY = new ShowAllMessageLoggerFactory();

	static enum LogName {
		FULL, SIMPLE
	}

}
