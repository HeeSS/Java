import java.applet.*;
import java.awt.Graphics;
/*<applet code="Param" width="300" height="80">
<param name="degree" value="20">
<param name="Message" value="¾È³ç"> </applet>*/
public class Param extends Applet{
	String m1, m2;
	double size;
	public void start(){
		m1=getParameter("Message");
		if(m1==null)	m1="No good";
		m2=getParameter("degree");
		if(m2==null)	size=5;
		else		size=Double.parseDouble(m2);
	}
	public void paint(Graphics g){
		g.drawString("Message received: "+m1, 0, 15);
		g.drawString("Degree: "+size, 0, 40);
	}
}