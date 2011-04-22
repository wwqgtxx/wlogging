package net.sf.wlogging;

import java.io.PrintStream;
import java.lang.reflect.Constructor;

import net.sf.readxml.ReadXml;
import net.sf.wlogging.ps.WLogErrPrintStream;
import net.sf.wlogging.ps.WLogOutPrintStream;
import net.sf.wlogging.ps.WLogPrintStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LogSystem {
	protected static final PrintStream out = System.out;
	protected static final PrintStream err = System.err;

	public static PrintStream getOut() {
		return out;
	}

	public static PrintStream getErr() {
		return err;
	}

	private static WLogPrintStream wlpsdebug, wlpsstart, wlpsinfo, wlpswarn,
			wlpsfatal, wlpserr, wlpsdebug2, wlpsstart2, wlpsinfo2, wlpswarn2,
			wlpsfatal2, wlpserr2;

	static {
		wlpsdebug = new WLogOutPrintStream(out, "   " + "[DEBUG]" + "   ", 4);
		wlpsstart = new WLogOutPrintStream(out, "   " + "[START]" + "   ", 4);
		wlpsinfo = new WLogOutPrintStream(out, "   " + "[INFO]" + "   ", 4);
		wlpswarn = new WLogErrPrintStream(out, "   " + "[WARN]" + "   ", 5);
		wlpsfatal = new WLogErrPrintStream(err, "   " + "[FATAL]" + "   ", 5);
		wlpserr = new WLogErrPrintStream(err, "   " + "[ERROR]" + "   ", 5);

		wlpsdebug2 = new WLogOutPrintStream(out, "   " + "[DEBUG]" + "   ", 5);
		wlpsstart2 = new WLogOutPrintStream(out, "   " + "[START]" + "   ", 5);
		wlpsinfo2 = new WLogOutPrintStream(out, "   " + "[INFO]" + "   ", 5);
		wlpswarn2 = new WLogErrPrintStream(out, "   " + "[WARN]" + "   ", 6);
		wlpsfatal2 = new WLogErrPrintStream(err, "   " + "[FATAL]" + "   ", 6);
		wlpserr2 = new WLogErrPrintStream(err, "   " + "[ERROR]" + "   ", 6);
	}

	public static void readXml(String dir, Object object) {
		try {
			Node[] node = ReadXml.getXmlNodeWithclass(dir, object);
			wlpsdebug = getwps("wlpsdebug", node);
			wlpsstart = getwps("wlpsstart", node);
			wlpsinfo = getwps("wlpsinfo", node);
			wlpswarn = getwps("wlpswarn", node);
			wlpsfatal = getwps("wlpsfatal", node);
			wlpserr = getwps("wlpserr", node);
			wlpsdebug2 = getwps("wlpsdebug2", node);
			wlpsstart2 = getwps("wlpsstart2", node);
			wlpsinfo2 = getwps("wlpsinfo2", node);
			wlpswarn2 = getwps("wlpswarn2", node);
			wlpsfatal2 = getwps("wlpsfatal2", node);
			wlpserr2 = getwps("wlpserr2", node);
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

	public static void readXml(Object object) {
		readXml("wlogging.xml", object);
	}

	private LogSystem() {
	}

	/**
	 * @see LogPainter
	 */
	public static LogPainter paint = new LogPainter(wlpsdebug, wlpsstart,
			wlpsinfo, wlpswarn, wlpsfatal, wlpserr);

	public static LogPainter.Log log = paint.new Log(wlpsdebug2, wlpsstart2,
			wlpsinfo2, wlpswarn2, wlpsfatal2, wlpserr2);

	public void log(Level l, Object o) {
		log.log(l, o);
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
}
