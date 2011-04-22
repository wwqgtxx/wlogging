package net.sf.wlogging;

import net.sf.wlogging.ps.WLogPrintStream;

/**
 * 
 * ��־�����<br/>
 * 
 * Ϊ�������ṩʹ�÷���<br/>
 * 
 * �з������£�<br/>
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
	 * ���debug��Ϣ <br/>
	 * ��ʾ�������־Ϊһ��������Ϣ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void debug(Object o) {
		wlpsdebug.println(o);
	}

	/**
	 * ���debug��Ϣ�����Զ����У�<br/>
	 * ��ʾ�������־Ϊһ��������Ϣ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void debugNl(Object o) {
		wlpsdebug.print(o);
	}

	/**
	 * ���������Ϣ<br/>
	 */
	public void start() {
		wlpsstart.println("start!");
	}

	/**
	 * ���info��Ϣ <br/>
	 * ��ʾ�������־��һ��ϵͳ��ʾ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void info(Object o) {
		wlpsinfo.println(o);
	}

	/**
	 * ���info��Ϣ�����Զ����У�<br/>
	 * ��ʾ�������־��һ��ϵͳ��ʾ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void infoNl(Object o) {
		wlpsinfo.print(o);
	}

	/**
	 * ���warn��Ϣ<br/>
	 * ��ʾ�������־��һ��������Ϣ<br/>
	 * 
	 * @param e
	 *            �쳣����
	 */
	public void warn(Exception e) {
		wlpswarn.println(e);
	}

	/**
	 * ���warn��Ϣ<br/>
	 * ��ʾ�������־��һ��������Ϣ<br/>
	 * 
	 * @param o
	 *            warn��Ϣ
	 */
	public void warn(Object o) {
		wlpswarn.println(new PaintMessageException(o.toString()));
	}

	/**
	 * ���fatal��Ϣ <br/>
	 * ��ʾ�������־��һ������ϵͳ�������ش���<br/>
	 * �������Զ�ֹͣ����<br/>
	 * 
	 * @param e
	 *            �쳣����
	 */
	public void fatal(Throwable e) {
		wlpsfatal.println(e);
		System.exit(1);
	}

	/**
	 * ���fatal��Ϣ <br/>
	 * ��ʾ�������־��һ������ϵͳ�������ش���<br/>
	 * �������Զ�ֹͣ����<br/>
	 * 
	 * @param o
	 *            fatal��Ϣ
	 */
	public void fatal(Object o) {
		wlpsfatal.println(new PaintMessageException(o.toString()));
		System.exit(1);
	}

	/**
	 * ���error��Ϣ<br/>
	 * ��ʾ�������־��һ��ϵͳ����<br/>
	 * 
	 * @param e
	 *            �쳣����
	 */
	public void error(Error e) {
		wlpserr.println(e);
	}

	/**
	 * ���error��Ϣ<br/>
	 * ��ʾ�������־��һ��ϵͳ����<br/>
	 * 
	 * @param o
	 *            fatal��Ϣ
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
