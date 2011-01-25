package net.sf.wlogging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintName {
	
	private String s;
	private static boolean can = true;
	private static final String INFO = "   "+"[INFO]"+"   ";
	private static final String ERR = "   "+"[ERROR]"+"   ";
	private static final String RN = "\r\n";
	private static final String NULL = "";

	
	@SuppressWarnings("rawtypes")
	public PrintName (Class c) {
		this.s=c.getName();
	}
	public static boolean setPaint (boolean c) {
		can = c;
		return can;
	}
	
	public String getTime() {
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  return df.format(new Date());
	}
	
	private void paintTo(String x,String n,Object o) {
		String a = getTime()+x+"("+s+")"+": "+ o+n ;
		if(can == true) {
			if(x.equals(ERR)){
				System.err.print(a);
			}
			else if(x.equals(NULL)){
				System.out.print(o);
			}
			else if(o.toString().equals(NULL)){
				System.out.println();
			}
			else{
				System.out.print(a);
			}
			
		}

	}
	
	public void paint(Object o){
		paintTo(INFO,RN,o);
	}
	
	public void paintDhl(Object o){
		paintTo(INFO,NULL,o);
	}
	
	public void paintErr(Exception e){
//		Display display = Display.getDefault();	
//		Color red= display.getSystemColor(SWT.COLOR_RED);					
//		StyledText st = ps.getTxtDebug();
//		st.setForeground(red);
		
		StringWriter sw=new StringWriter(); 
		PrintWriter pw=new PrintWriter(sw); 
		e.printStackTrace(pw); 
		String se =(sw.toString()); 
		
		paintTo(ERR,RN,se);

		
	}
	
}


