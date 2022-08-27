import java.applet.*;
import java.awt.Graphics;
/*<applet code="RectangleTest" width="400" height="150"></applet>*/
public class RectangleTest extends Applet{
	public void paint(Graphics g){
		g.drawLine(20, 40, 160, 80);
		g.drawLine(180, 20, 50, 90);

		g.drawRect(220, 20, 50, 50);
		g.fillRect(280, 20, 50, 50);
		g.drawRoundRect(220, 90, 50, 50, 20, 20);
		g.fillRoundRect(280, 90, 50, 50, 60, 60);
	}
}