/*<applet code="Hello" eidth="200" height="200"></applet>*/

import java.applet.Applet;
import java.awt.*;


public class Hello extends Applet{
	public void paint(Graphics g){
		g.drawString("Hello java", 50, 30);
	}
}