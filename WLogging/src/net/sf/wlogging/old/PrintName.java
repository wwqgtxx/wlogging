package net.sf.wlogging.old;

import java.io.PrintStream;

import net.sf.wlogging.LogSystem;
import net.sf.wlogging.PaintMessageException;
import net.sf.wlogging.ps.WLogErrPrintStream;
import net.sf.wlogging.ps.WLogOutPrintStream;
import net.sf.wlogging.ps.WLogPrintStream;

/**
 * ��־����<br/>
 * 
 * ʹ�÷�����PrintName.paint������<br/>
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
	 * ��־�������<br/>
	 * 
	 * Ϊ�������ṩʹ�÷���<br/>
	 * 
	 * <br/>
	 * 
	 * ��ʹ��"import net.sf.wlogging.old.PrintName.paint;"��ÿ���ļ���λ<br/>
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
		 * ���debug��Ϣ <br/>
		 * ��ʾ�������־Ϊһ��������Ϣ<br/>
		 * 
		 * @param o
		 *            ��Ϣ����
		 */
		@Deprecated
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
		@Deprecated
		public static void debugNl(Object o) {
			wlpsdebug.print(o);
		}

		/**
		 * ���������Ϣ<br/>
		 */
		@Deprecated
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
		@Deprecated
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
		@Deprecated
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
		@Deprecated
		public static void warn(Exception e) {
			wlpswarn.println(e);
		}

		@Deprecated
		public static void warn(Object o) {
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
		@Deprecated
		public static void fatal(Throwable e) {
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
		@Deprecated
		public static void fatal(Object o) {
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
		@Deprecated
		public static void error(Error e) {
			wlpserr.println(e);
		}

		/**
		 * ���error��Ϣ<br/>
		 * ��ʾ�������־��һ��ϵͳ����<br/>
		 * 
		 * @param o
		 *            fatal��Ϣ
		 */
		@Deprecated
		public static void error(Object o) {
			wlpserr.println(new PaintMessageException(o.toString()));
		}
	}

}
