package net.sf.wlogging;

import java.io.PrintStream;
import java.lang.reflect.Constructor;

import net.sf.readxml.ReadXml;
import net.sf.wlogging.ps.WLogErrPrintStream;
import net.sf.wlogging.ps.WLogOutPrintStream;
import net.sf.wlogging.ps.WLogPrintStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class LogSystem {
	protected static final PrintStream out = System.out;
	protected static final PrintStream err = System.err;
	private static int NUM = 6;

	public static PrintStream getOut() {
		return out;
	}

	public static PrintStream getErr() {
		return err;
	}

	private static WLogPrintStream wlpsdebug, wlpsstart, wlpsinfo, wlpswarn,
			wlpsfatal, wlpserr;

	static {
		wlpsdebug = new WLogOutPrintStream(out, "   " + "[DEBUG]" + "   ", NUM);
		wlpsstart = new WLogOutPrintStream(out, "   " + "[START]" + "   ", NUM);
		wlpsinfo = new WLogOutPrintStream(out, "   " + "[INFO]" + "   ", NUM);
		wlpswarn = new WLogErrPrintStream(out, "   " + "[WARN]" + "   ",
				NUM + 1);
		wlpsfatal = new WLogErrPrintStream(err, "   " + "[FATAL]" + "   ",
				NUM + 1);
		wlpserr = new WLogErrPrintStream(err, "   " + "[ERROR]" + "   ",
				NUM + 1);

	}

	@Deprecated
	public static void readXml(String dir, Object object) {
		try {
			Node[] node = ReadXml.getXmlNodeWithclass(dir, object);
			wlpsdebug = getwps("wlpsdebug", node);
			wlpsstart = getwps("wlpsstart", node);
			wlpsinfo = getwps("wlpsinfo", node);
			wlpswarn = getwps("wlpswarn", node);
			wlpsfatal = getwps("wlpsfatal", node);
			wlpserr = getwps("wlpserr", node);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static WLogPrintStream getwps(String s, Node[] node) {

		String s_wlpsdebug_Class = null, s_wlpsdebug_PrintStream = null, s_wlpsdebug_String = null, s_wlpsdebug_int = null;

		for (int i = 0; i < node.length; i++) {
			if (node[i].getNodeName().equalsIgnoreCase(s)) {
				NodeList nl = node[i].getChildNodes();
				for (int ii = 0; ii < nl.getLength(); ii++) {
					if (nl.item(ii).getNodeName().equalsIgnoreCase("Class")) {
						s_wlpsdebug_Class = nl.item(ii).getFirstChild()
								.getNodeValue();
					}
					if (nl.item(ii).getNodeName()
							.equalsIgnoreCase("PrintStream")) {
						s_wlpsdebug_PrintStream = nl.item(ii).getFirstChild()
								.getNodeValue();
					}
					if (nl.item(ii).getNodeName().equalsIgnoreCase("String")) {
						s_wlpsdebug_String = nl.item(ii).getFirstChild()
								.getNodeValue();
					}
					if (nl.item(ii).getNodeName().equalsIgnoreCase("int")) {
						s_wlpsdebug_int = nl.item(ii).getFirstChild()
								.getNodeValue();
					}
				}

			}

		}

		Class<?> c = null;
		try {
			System.out.println(s_wlpsdebug_Class);
			c = Class.forName(s_wlpsdebug_Class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class<?>[] ptype = new Class[] { PrintStream.class, String.class,
				int.class };
		Constructor<?> ctor = null;
		try {
			ctor = c.getConstructor(ptype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object[] obj = null;

		if (s_wlpsdebug_PrintStream.equalsIgnoreCase("out")) {
			obj = new Object[] { out, s_wlpsdebug_String,
					Integer.parseInt(s_wlpsdebug_int) };
		}
		if (s_wlpsdebug_PrintStream.equalsIgnoreCase("err")) {
			obj = new Object[] { out, s_wlpsdebug_String,
					Integer.parseInt(s_wlpsdebug_int) };
		}

		WLogPrintStream o_wlpsdebug = null;
		try {
			o_wlpsdebug = (WLogPrintStream) ctor.newInstance(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return o_wlpsdebug;

	}

	@Deprecated
	public static void readXml(Object object) {
		readXml("wlogging.xml", object);
	}

	private LogSystem() {
	}

	static LogPainter log = new LogPainter(wlpsdebug, wlpsstart, wlpsinfo,
			wlpswarn, wlpsfatal, wlpserr);

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
