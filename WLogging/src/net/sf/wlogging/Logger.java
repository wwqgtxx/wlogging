package net.sf.wlogging;

public class Logger {
	private String string;

	protected Logger(String string) {
		this.string = string;
	}

	protected Logger() {
		this.string = "Anonymous";
	}

	public void paint(Level l, Object o) {
		LogSystem.log.log(l, string, o.toString());
	}

	public void paintNl(Level l, Object o) {
		LogSystem.log.logNl(l, string, o.toString());
	}

	public void log(Level l, Object o) {
		LogSystem.log.log(l, string, o.toString());
	}

	public void logNl(Level l, Object o) {
		LogSystem.log.logNl(l, string, o.toString());
	}

	/**
	 * 输出debug消息 <br/>
	 * 表示输出的日志为一个调试信息<br/>
	 * 
	 * @param o
	 *            消息内容
	 */
	public void debug(Object o) {
		paint(Level.DEBUG, o);
	}

	/**
	 * 输出debug消息（不自动换行）<br/>
	 * 表示输出的日志为一个调试信息<br/>
	 * 
	 * @param o
	 *            消息内容
	 */
	public void debugNl(Object o) {
		paintNl(Level.DEBUG, o);
	}

	/**
	 * 输出启动消息<br/>
	 */
	public void start() {
		paint(Level.START, "start!");
	}

	/**
	 * 输出info消息 <br/>
	 * 表示输出的日志是一个系统提示<br/>
	 * 
	 * @param o
	 *            消息内容
	 */
	public void info(Object o) {
		paint(Level.INFO, o);
	}

	/**
	 * 输出info消息（不自动换行）<br/>
	 * 表示输出的日志是一个系统提示<br/>
	 * 
	 * @param o
	 *            消息内容
	 */
	public void infoNl(Object o) {
		paintNl(Level.INFO, o);
	}

	/**
	 * 输出warn消息<br/>
	 * 表示输出的日志是一个警告信息<br/>
	 * 
	 * @param e
	 *            异常对象
	 */
	public void warn(Exception e) {
		paint(Level.WARN, e);
	}

	/**
	 * 输出warn消息<br/>
	 * 表示输出的日志是一个警告信息<br/>
	 * 
	 * @param o
	 *            warn消息
	 */
	public void warn(Object o) {
		paint(Level.WARN, new PaintMessageException(o.toString()));
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
		paint(Level.FATAL, e);
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
		paint(Level.FATAL, new PaintMessageException(o.toString()));
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
		paint(Level.ERROR, e);
	}

	/**
	 * 输出error消息<br/>
	 * 表示输出的日志是一个系统错误<br/>
	 * 
	 * @param o
	 *            fatal消息
	 */
	public void error(Object o) {
		paint(Level.ERROR, o);
	}

}
