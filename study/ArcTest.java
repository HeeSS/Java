import java.applet.*;
import java.awt.Graphics;
/*<applet code="ArcTest" width="400" height="150"></applet>*/
public class ArcTest extends Applet{
	public void paint(Graphics g){
		g.drawOval(40,20,50,50);
		g.fillOval(100,20,50,80);
		g.drawArc(200,20,50,50,90,180);
		g.fillArc(260,20,50,80,45,240);
	}
}