/*<applet code="ExecSeq" width="200" height="200"></applet>*/
import java.applet.*;
import java.awt.*;
public class ExecSeq extends Applet{
	int init=0, start=0, stop=0;
	String msg1, msg2, msg3;
	public void init(){init++;}
	public void start(){start++;}
	public void stop(){stop++;}
	public void paint(Graphics g){
		msg1="The number of init() call: "+init;
		msg2="The number of start() call: "+start;
		msg3="The number of stop() call: "+stop;
		g.drawString(msg1, 20, 40);
		g.drawString(msg2, 20, 60);
		g.drawString(msg3, 20, 80);
	}
}