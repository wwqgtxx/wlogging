package net.sf.wlogging;

import java.io.PrintStream;

/**
 * 日志主类<br/>
 * 
 * 使用方法在PrintName.paint子类中<br/>
 * 
 * @see PrintName.paint
 * 
 */
public class PrintName {

	public static final PrintStream out = System.out;
	public static final PrintStream err = System.err;

	static {
		WLogPrintStream wlps = new WLogOutPrintStream(out, "   " + "[OUT]"
				+ "   ");
		System.setOut(wlps);

		WLogPrintStream wlpserr = new WLogErrPrintStream(err, "   " + "[ERROR]"
				+ "   ", 4);
		System.setErr(wlpserr);
	}

	/**
	 * 
	 * 日志输出子类<br/>
	 * 
	 * 为调用者提供使用方法<br/>
	 * 
	 * <br/>
	 * 
	 * 请使用"import net.sf.wlogging.PrintName.paint;"在每个文件首位<br/>
	 * 
	 * <br/>
	 * 
	 * 有方法如下：<br/>
	 * 
	 * public static void debug(Object o)<br/>
	 * 
	 * public static void debugNl(Object o)<br/>
	 * 
	 * public static void start()<br/>
	 * 
	 * public static void info(Object o)<br/>
	 * 
	 * public static void infoNl(Object o)<br/>
	 * 
	 * public static void warn(Exception e)<br/>
	 * 
	 * public static void fatal(Throwable e)<br/>
	 * 
	 * public static void error(Error e)<br/>
	 * 
	 * 
	 */
	public static class paint {
		private static WLogPrintStream wlpsdebug = new WLogOutPrintStream(out,
				"   " + "[DEBUG]" + "   ", 4);
		private static WLogPrintStream wlpsstart = new WLogOutPrintStream(out,
				"   " + "[START]" + "   ", 4);
		private static WLogPrintStream wlpsinfo = new WLogOutPrintStream(out,
				"   " + "[INFO]" + "   ", 4);
		private static WLogPrintStream wlpswarn = new WLogErrPrintStream(out,
				"   " + "[WARN]" + "   ", 4);
		private static WLogPrintStream wlpsfatal = new WLogErrPrintStream(err,
				"   " + "[FATAL]" + "   ", 4);
		private static WLogPrintStream wlpserr = new WLogErrPrintStream(err,
				"   " + "[ERROR]" + "   ", 4);

		/**
		 * 输出debug消息 <br/>
		 * 表示输出的日志为一个调试信息<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public static void debug(Object o) {
			wlpsdebug.println(o);
		}

		/**
		 * 输出debug消息（不自动换行）<br/>
		 * 表示输出的日志为一个调试信息<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public static void debugNl(Object o) {
			wlpsdebug.print(o);
		}

		/**
		 * 输出启动消息<br/>
		 */
		public static void start() {
			wlpsstart.println("start!");
		}

		/**
		 * 输出info消息 <br/>
		 * 表示输出的日志是一个系统提示<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public static void info(Object o) {
			wlpsinfo.println(o);
		}

		/**
		 * 输出info消息（不自动换行）<br/>
		 * 表示输出的日志是一个系统提示<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		public static void infoNl(Object o) {
			wlpsinfo.print(o);
		}

		/**
		 * 输出warn消息<br/>
		 * 表示输出的日志是一个警告信息<br/>
		 * 
		 * @param e
		 *            异常对象
		 */
		public static void warn(Exception e) {
			wlpswarn.println(e);
		}

		/**
		 * 输出fatal消息 <br/>
		 * 表示输出的日志是一个导致系统崩溃严重错误<br/>
		 * 
		 * @param e
		 *            异常对象
		 */
		public static void fatal(Throwable e) {
			wlpsfatal.println(e);
		}

		/**
		 * 输出error消息<br/>
		 * 表示输出的日志是一个系统错误<br/>
		 * 
		 * @param e
		 *            异常对象
		 */
		public static void error(Error e) {
			wlpserr.println(e);
		}
	}

}
