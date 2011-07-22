package net.sf.wlogging;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

import net.sf.wlogging.appender.Appender;
import net.sf.wlogging.appender.SysOutAppender;
import net.sf.wlogging.ps.WLogErrPrintStream;
import net.sf.wlogging.ps.WLogOutPrintStream;
import net.sf.wlogging.ps.WLogPrintStream;

import org.w3c.dom.Node;

public final class LogSystem {
	private final static String APPENDER_NAME = "net.sf.wlogging.appender";
	private final static String NUM_NAME = "net.sf.wlogging.num";
	private static Appender appender = null;
	private static PrintStream out = null;
	private static PrintStream err = null;
	private static int NUM = 0;
	private static WLogPrintStream wlpsdebug, wlpsstart, wlpsinfo, wlpswarn,
			wlpsfatal, wlpserr;
	static {
		init1();
		init2(out, err);
		readSet(new LogSystem());
	}

	static LogPainter log = new LogPainter(wlpsdebug, wlpsstart, wlpsinfo,
			wlpswarn, wlpsfatal, wlpserr);

	public static PrintStream getOut() {
		return out;
	}

	public static PrintStream getErr() {
		return err;
	}

	private synchronized static void init1() {
		appender = SysOutAppender.getAppender();
		out = appender.getOut();
		err = appender.getErr();
		NUM = 6;
	}

	private synchronized static void init2(PrintStream out, PrintStream err) {
		wlpsdebug = new WLogOutPrintStream(out, "   " + "[DEBUG]" + "   ", NUM);
		wlpsstart = new WLogOutPrintStream(out, "   " + "[START]" + "   ", NUM);
		wlpsinfo = new WLogOutPrintStream(out, "   " + "[INFO]" + "   ", NUM);
		wlpswarn = new WLogErrPrintStream(out, "   " + "[WARN]" + "   ",
				NUM + 1);
		wlpsfatal = new WLogErrPrintStream(err, "   " + "[FATAL]" + "   ",
				NUM + 1);
		wlpserr = new WLogErrPrintStream(err, "   " + "[ERROR]" + "   ",
				NUM + 1);
		if (log == null) {
			log = new LogPainter(wlpsdebug, wlpsstart, wlpsinfo, wlpswarn,
					wlpsfatal, wlpserr);
		} else {
			log.setPrintStream(wlpsdebug, wlpsstart, wlpsinfo, wlpswarn,
					wlpsfatal, wlpserr);
		}
	}

	public synchronized static void readSet(String xmldir, String propdir,
			Object object) {
		if (log != null && out != null && err != null && appender != null) {
			synchronized (log) {
				synchronized (out) {
					synchronized (err) {
						synchronized (appender) {
							readSet0(xmldir, propdir, object);
						}

					}
				}
			}
		} else {
			readSet0(xmldir, propdir, object);
		}

	}

	private static void readSet0(String xmldir, String propdir, Object object) {
		int num2 = 0;
		Appender app = null;
		try {
			Node[] node = ReadXml.getXmlNodeWithclass(xmldir, object);
			app = getAppender(node);
			num2 = Integer.parseInt(ReadXml.getString("num", node));

		} catch (Exception e) {
		}
		if (app == null) {
			Properties prop = new Properties();
			try {
				FileInputStream in = new FileInputStream(object.getClass()
						.getClassLoader().getResource(propdir).getPath());
				prop.load(in);
				in.close();
				app = getAppender(prop);
				num2 = Integer.parseInt(prop.getProperty(NUM_NAME));
				prop.clear();
			} catch (Exception e) {
				prop.clear();
			}
		}
		if (num2 != 0) {
			NUM = num2;
		}
		if (app != null) {
			appender = app;
			out = appender.getOut();
			err = appender.getErr();
			init2(out, err);
		}

	}

	private static Appender getAppender(Node[] node) {
		try {
			Class<?> class1 = Class
					.forName(ReadXml.getString("appender", node));
			Appender appender = (Appender) class1.getMethod("getAppender")
					.invoke(null);
			return appender;
		} catch (Exception e) {
			return null;
		}
	}

	private static Appender getAppender(Properties prop) {
		try {
			Class<?> class1 = Class.forName(getString(APPENDER_NAME, prop));
			Appender appender = (Appender) class1.getMethod("getAppender")
					.invoke(null);
			return appender;
		} catch (Exception e) {
			return null;
		}
	}

	private static String getString(String s, Properties prop) {
		try {
			String class_name = null;
			class_name = prop.getProperty(s);
			return class_name;
		} catch (Exception e) {
			return null;
		}

	}

	public static void readSet(Object object) {
		readSet("wlogging.xml", "wlogging.properties", object);
	}

	private LogSystem() {
	}

	@Deprecated
	public static void log(Level l, Object o) {
		log.log(l, o);
	}

	@Deprecated
	public static void log(Level l, String string, String s) {
		log.log(l, string, s);
	}

	@Deprecated
	public static void logNl(Level l, Object o) {
		log.logNl(l, o);
	}

	@Deprecated
	public static void logNl(Level l, String string, String s) {
		log.logNl(l, string, s);
	}

	public static void setOutErrInWLog(boolean b) {
		if (b) {
			WLogPrintStream wlps = new WLogOutPrintStream(out, "   " + "[OUT]"
					+ "   ");
			System.setOut(wlps);

			WLogPrintStream wlpserr = new WLogErrPrintStream(err, "   "
					+ "[ERROR]" + "   ", 4);
			System.setErr(wlpserr);
		} else {
			System.setOut(out);
			System.setErr(err);
		}
	}

	static class LogPainter {

		private WLogPrintStream wlpsdebug = null;
		private WLogPrintStream wlpsstart = null;
		private WLogPrintStream wlpsinfo = null;
		private WLogPrintStream wlpswarn = null;
		private WLogPrintStream wlpsfatal = null;
		private WLogPrintStream wlpserr = null;

		public LogPainter(WLogPrintStream wlpsdebug, WLogPrintStream wlpsstart,
				WLogPrintStream wlpsinfo, WLogPrintStream wlpswarn,
				WLogPrintStream wlpsfatal, WLogPrintStream wlpserr) {
			this.wlpsdebug = wlpsdebug;
			this.wlpserr = wlpserr;
			this.wlpsfatal = wlpsfatal;
			this.wlpsinfo = wlpsinfo;
			this.wlpsstart = wlpsstart;
			this.wlpswarn = wlpswarn;

		}

		private synchronized void setPrintStream(WLogPrintStream wlpsdebug,
				WLogPrintStream wlpsstart, WLogPrintStream wlpsinfo,
				WLogPrintStream wlpswarn, WLogPrintStream wlpsfatal,
				WLogPrintStream wlpserr) {
			this.wlpsdebug = wlpsdebug;
			this.wlpserr = wlpserr;
			this.wlpsfatal = wlpsfatal;
			this.wlpsinfo = wlpsinfo;
			this.wlpsstart = wlpsstart;
			this.wlpswarn = wlpswarn;
		}

		/**
		 * 输出debug消息 <br/>
		 * 表示输出的日志为一个调试信息<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public void debug(Object o) {
			wlpsdebug.println(o);
		}

		/**
		 * 输出debug消息（不自动换行）<br/>
		 * 表示输出的日志为一个调试信息<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public void debugNl(Object o) {
			wlpsdebug.print(o);
		}

		/**
		 * 输出启动消息<br/>
		 */
		public void start() {
			wlpsstart.println("start!");
		}

		/**
		 * 输出info消息 <br/>
		 * 表示输出的日志是一个系统提示<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public void info(Object o) {
			wlpsinfo.println(o);
		}

		/**
		 * 输出info消息（不自动换行）<br/>
		 * 表示输出的日志是一个系统提示<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public void infoNl(Object o) {
			wlpsinfo.print(o);
		}

		/**
		 * 输出warn消息<br/>
		 * 表示输出的日志是一个警告信息<br/>
		 * 
		 * @param e
		 *            异常对象
		 */
		public void warn(Exception e) {
			wlpswarn.println(e);
		}

		/**
		 * 输出warn消息<br/>
		 * 表示输出的日志是一个警告信息<br/>
		 * 
		 * @param o
		 *            warn消息
		 */
		public void warn(Object o) {
			wlpswarn.println(new PaintMessageException(o.toString()));
		}

		/**
		 * 输出fatal消息 <br/>
		 * 表示输出的日志是一个导致系统崩溃严重错误<br/>
		 * 输出后会自动停止程序<br/>
		 * 
		 * @param e
		 *            异常对象
		 */
		public void fatal(Throwable e) {
			wlpsfatal.println(e);
			System.exit(1);
		}

		/**
		 * 输出fatal消息 <br/>
		 * 表示输出的日志是一个导致系统崩溃严重错误<br/>
		 * 输出后会自动停止程序<br/>
		 * 
		 * @param o
		 *            fatal消息
		 */
		public void fatal(Object o) {
			wlpsfatal.println(new PaintMessageException(o.toString()));
			System.exit(1);
		}

		/**
		 * 输出error消息<br/>
		 * 表示输出的日志是一个系统错误<br/>
		 * 
		 * @param e
		 *            异常对象
		 */
		public void error(Error e) {
			wlpserr.println(e);
		}

		/**
		 * 输出error消息<br/>
		 * 表示输出的日志是一个系统错误<br/>
		 * 
		 * @param o
		 *            fatal消息
		 */
		public void error(Object o) {
			wlpserr.println(new PaintMessageException(o.toString()));
		}

		public void log(Level l, Object o) {
			switch (l) {
			case DEBUG:
				wlpsdebug.println(o);
				break;
			case ERROR:
				wlpserr.println(o);
				break;
			case FATAL:
				wlpsfatal.println(o);
				break;
			case INFO:
				wlpsinfo.println(o);
				break;
			case START:
				wlpsstart.println(o);
				break;
			case WARN:
				wlpswarn.println(o);
				break;

			}
		}

		public void log(Level l, String name, Object o) {
			switch (l) {
			case DEBUG:
				wlpsdebug.printlnn(name, o);
				break;
			case ERROR:
				wlpserr.printlnn(name, o);
				break;
			case FATAL:
				wlpsfatal.printlnn(name, o);
				break;
			case INFO:
				wlpsinfo.printlnn(name, o);
				break;
			case START:
				wlpsstart.printlnn(name, o);
				break;
			case WARN:
				wlpswarn.printlnn(name, o);
				break;

			}
		}

		public void logNl(Level l, Object o) {
			switch (l) {
			case DEBUG:
				wlpsdebug.print(o);
				break;
			case ERROR:
				wlpserr.print(o);
				break;
			case FATAL:
				wlpsfatal.print(o);
				break;
			case INFO:
				wlpsinfo.print(o);
				break;
			case START:
				wlpsstart.print(o);
				break;
			case WARN:
				wlpswarn.print(o);
				break;

			}
		}

		public void logNl(Level l, String name, Object o) {
			switch (l) {
			case DEBUG:
				wlpsdebug.printn(name, o);
				break;
			case ERROR:
				wlpserr.printn(name, o);
				break;
			case FATAL:
				wlpsfatal.printn(name, o);
				break;
			case INFO:
				wlpsinfo.printn(name, o);
				break;
			case START:
				wlpsstart.printn(name, o);
				break;
			case WARN:
				wlpswarn.printn(name, o);
				break;

			}
		}

	}

}
