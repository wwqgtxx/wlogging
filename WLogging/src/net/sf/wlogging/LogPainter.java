package net.sf.wlogging;

import net.sf.wlogging.ps.WLogPrintStream;

/**
 * 
 * 日志输出类<br/>
 * 
 * 为调用者提供使用方法<br/>
 * 
 * 有方法如下：<br/>
 * 
 * public void debug(Object o)<br/>
 * 
 * public void debugNl(Object o)<br/>
 * 
 * public void start()<br/>
 * 
 * public void info(Object o)<br/>
 * 
 * public void infoNl(Object o)<br/>
 * 
 * public void warn(Object o)<br/>
 * 
 * public void fatal(Object o)<br/>
 * 
 * public void error(Object o)<br/>
 * 
 * public void warn(Exception e)<br/>
 * 
 * public void fatal(Throwable e)<br/>
 * 
 * public void error(Error e)<br/>
 * 
 * 
 */
public class LogPainter {

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
		LogSystem.log.log(l, o);
	}

	public void log(Level l, String name, Object o) {
		LogSystem.log.log(l, o);
	}

	public class Log {

		private WLogPrintStream wlpsdebug = null;
		private WLogPrintStream wlpsstart = null;
		private WLogPrintStream wlpsinfo = null;
		private WLogPrintStream wlpswarn = null;
		private WLogPrintStream wlpsfatal = null;
		private WLogPrintStream wlpserr = null;

		public Log(WLogPrintStream wlpsdebug, WLogPrintStream wlpsstart,
				WLogPrintStream wlpsinfo, WLogPrintStream wlpswarn,
				WLogPrintStream wlpsfatal, WLogPrintStream wlpserr) {
			this.wlpsdebug = wlpsdebug;
			this.wlpserr = wlpserr;
			this.wlpsfatal = wlpsfatal;
			this.wlpsinfo = wlpsinfo;
			this.wlpsstart = wlpsstart;
			this.wlpswarn = wlpswarn;

		}

		public void print(Level l, Object o) {
			log(l, o);
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
	}

}
