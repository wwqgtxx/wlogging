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
	 * ���debug��Ϣ <br/>
	 * ��ʾ�������־Ϊһ��������Ϣ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void debug(Object o) {
		paint(Level.DEBUG, o);
	}

	/**
	 * ���debug��Ϣ�����Զ����У�<br/>
	 * ��ʾ�������־Ϊһ��������Ϣ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void debugNl(Object o) {
		paintNl(Level.DEBUG, o);
	}

	/**
	 * ���������Ϣ<br/>
	 */
	public void start() {
		paint(Level.START, "start!");
	}

	/**
	 * ���info��Ϣ <br/>
	 * ��ʾ�������־��һ��ϵͳ��ʾ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void info(Object o) {
		paint(Level.INFO, o);
	}

	/**
	 * ���info��Ϣ�����Զ����У�<br/>
	 * ��ʾ�������־��һ��ϵͳ��ʾ<br/>
	 * 
	 * @param o
	 *            ��Ϣ����
	 */
	public void infoNl(Object o) {
		paintNl(Level.INFO, o);
	}

	/**
	 * ���warn��Ϣ<br/>
	 * ��ʾ�������־��һ��������Ϣ<br/>
	 * 
	 * @param e
	 *            �쳣����
	 */
	public void warn(Exception e) {
		paint(Level.WARN, e);
	}

	/**
	 * ���warn��Ϣ<br/>
	 * ��ʾ�������־��һ��������Ϣ<br/>
	 * 
	 * @param o
	 *            warn��Ϣ
	 */
	public void warn(Object o) {
		paint(Level.WARN, new PaintMessageException(o.toString()));
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
		paint(Level.FATAL, e);
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
		paint(Level.FATAL, new PaintMessageException(o.toString()));
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
		paint(Level.ERROR, e);
	}

	/**
	 * ���error��Ϣ<br/>
	 * ��ʾ�������־��һ��ϵͳ����<br/>
	 * 
	 * @param o
	 *            fatal��Ϣ
	 */
	public void error(Object o) {
		paint(Level.ERROR, o);
	}

}
