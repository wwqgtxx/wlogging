package net.sf.wlogging;

import java.io.PrintStream;

/**
 * ��־����<br/>
 * 
 * ʹ�÷�����PrintName.paint������<br/>
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
	 * ��־�������<br/>
	 * 
	 * Ϊ�������ṩʹ�÷���<br/>
	 * 
	 * <br/>
	 * 
	 * ��ʹ��"import net.sf.wlogging.PrintName.paint;"��ÿ���ļ���λ<br/>
	 * 
	 * <br/>
	 * 
	 * �з������£�<br/>
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
		 * ���debug��Ϣ <br/>
		 * ��ʾ�������־Ϊһ��������Ϣ<br/>
		 * 
		 * @param o
		 *            ��Ϣ����
		 */
		public static void debug(Object o) {
			wlpsdebug.println(o);
		}

		/**
		 * ���debug��Ϣ�����Զ����У�<br/>
		 * ��ʾ�������־Ϊһ��������Ϣ<br/>
		 * 
		 * @param o
		 *            ��Ϣ����
		 */
		public static void debugNl(Object o) {
			wlpsdebug.print(o);
		}

		/**
		 * ���������Ϣ<br/>
		 */
		public static void start() {
			wlpsstart.println("start!");
		}

		/**
		 * ���info��Ϣ <br/>
		 * ��ʾ�������־��һ��ϵͳ��ʾ<br/>
		 * 
		 * @param o
		 *            ��Ϣ����
		 */
		public static void info(Object o) {
			wlpsinfo.println(o);
		}

		/**
		 * ���info��Ϣ�����Զ����У�<br/>
		 * ��ʾ�������־��һ��ϵͳ��ʾ<br/>
		 * 
		 * @param o
		 *            ��Ϣ����
		 */
		public static void infoNl(Object o) {
			wlpsinfo.print(o);
		}

		/**
		 * ���warn��Ϣ<br/>
		 * ��ʾ�������־��һ��������Ϣ<br/>
		 * 
		 * @param e
		 *            �쳣����
		 */
		public static void warn(Exception e) {
			wlpswarn.println(e);
		}

		/**
		 * ���fatal��Ϣ <br/>
		 * ��ʾ�������־��һ������ϵͳ�������ش���<br/>
		 * 
		 * @param e
		 *            �쳣����
		 */
		public static void fatal(Throwable e) {
			wlpsfatal.println(e);
		}

		/**
		 * ���error��Ϣ<br/>
		 * ��ʾ�������־��һ��ϵͳ����<br/>
		 * 
		 * @param e
		 *            �쳣����
		 */
		public static void error(Error e) {
			wlpserr.println(e);
		}
	}

}
