package net.sf.wlogging;

import java.io.PrintStream;

import net.sf.wlogging.ps.WLogErrPrintStream;
import net.sf.wlogging.ps.WLogOutPrintStream;
import net.sf.wlogging.ps.WLogPrintStream;

public class WLogSystem {
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

	private static WLogPrintStream wlpsdebug = new WLogOutPrintStream(out,
			"   " + "[DEBUG]" + "   ", 4);
	private static WLogPrintStream wlpsstart = new WLogOutPrintStream(out,
			"   " + "[START]" + "   ", 4);
	private static WLogPrintStream wlpsinfo = new WLogOutPrintStream(out, "   "
			+ "[INFO]" + "   ", 4);
	private static WLogPrintStream wlpswarn = new WLogErrPrintStream(out, "   "
			+ "[WARN]" + "   ", 5);
	private static WLogPrintStream wlpsfatal = new WLogErrPrintStream(err,
			"   " + "[FATAL]" + "   ", 5);
	private static WLogPrintStream wlpserr = new WLogErrPrintStream(err, "   "
			+ "[ERROR]" + "   ", 5);

	/**
	 * @see WLogPaint
	 */
	public static WLogPaint paint = new WLogPaint(wlpsdebug, wlpsstart,
			wlpsinfo, wlpswarn, wlpsfatal, wlpserr);
}
