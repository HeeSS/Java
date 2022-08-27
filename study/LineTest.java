import java.applet.*;
import java.awt.Graphics;
/*<applet code="LineTest" width="200" height="100"></applet>*/
public class LineTest extends Applet{
	public void paint(Graphics g){
		g.drawLine(20, 40, 160, 80);
		g.drawLine(180, 20, 50, 90);
	}
}