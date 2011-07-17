package net.sf.wlogging.old;

import java.io.PrintStream;

import net.sf.wlogging.LogSystem;
import net.sf.wlogging.PaintMessageException;
import net.sf.wlogging.ps.WLogErrPrintStream;
import net.sf.wlogging.ps.WLogOutPrintStream;
import net.sf.wlogging.ps.WLogPrintStream;

/**
 * 日志主类<br/>
 * 
 * 使用方法在PrintName.paint子类中<br/>
 * 
 * @see PrintName.paint
 * 
 */
@Deprecated
public class PrintName {

	@Deprecated
	public static final PrintStream out = LogSystem.getOut();
	@Deprecated
	public static final PrintStream err = LogSystem.getErr();

	/**
	 * 
	 * 日志输出子类<br/>
	 * 
	 * 为调用者提供使用方法<br/>
	 * 
	 * <br/>
	 * 
	 * 请使用"import net.sf.wlogging.old.PrintName.paint;"在每个文件首位<br/>
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
	 * public void warn(Object o)<br/>
	 * 
	 * public void fatal(Object o)<br/>
	 * 
	 * public void error(Object o)<br/>
	 * 
	 * public static void warn(Exception e)<br/>
	 * 
	 * public static void fatal(Throwable e)<br/>
	 * 
	 * public static void error(Error e)<br/>
	 * 
	 * 
	 */
	@Deprecated
	public static class paint {

		private static WLogPrintStream wlpsdebug = new WLogOutPrintStream(out,
				"   " + "[DEBUG]" + "   ", 4);
		private static WLogPrintStream wlpsstart = new WLogOutPrintStream(out,
				"   " + "[START]" + "   ", 4);
		private static WLogPrintStream wlpsinfo = new WLogOutPrintStream(out,
				"   " + "[INFO]" + "   ", 4);
		private static WLogPrintStream wlpswarn = new WLogErrPrintStream(out,
				"   " + "[WARN]" + "   ", 5);
		private static WLogPrintStream wlpsfatal = new WLogErrPrintStream(err,
				"   " + "[FATAL]" + "   ", 5);
		private static WLogPrintStream wlpserr = new WLogErrPrintStream(err,
				"   " + "[ERROR]" + "   ", 5);

		/**
		 * 输出debug消息 <br/>
		 * 表示输出的日志为一个调试信息<br/>
		 * 
		 * @param o
		 *            消息内容
		 */
		@Deprecated
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
		@Deprecated
		public static void debugNl(Object o) {
			wlpsdebug.print(o);
		}

		/**
		 * 输出启动消息<br/>
		 */
		@Deprecated
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
		@Deprecated
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
		@Deprecated
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
		@Deprecated
		public static void warn(Exception e) {
			wlpswarn.println(e);
		}

		@Deprecated
		public static void warn(Object o) {
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
		@Deprecated
		public static void fatal(Throwable e) {
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
		@Deprecated
		public static void fatal(Object o) {
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
		@Deprecated
		public static void error(Error e) {
			wlpserr.println(e);
		}

		/**
		 * 输出error消息<br/>
		 * 表示输出的日志是一个系统错误<br/>
		 * 
		 * @param o
		 *            fatal消息
		 */
		@Deprecated
		public static void error(Object o) {
			wlpserr.println(new PaintMessageException(o.toString()));
		}
	}

}
