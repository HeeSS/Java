import java.applet.*;
import java.awt.Graphics;
import java.awt.Color;
/*<applet code="ColorTest" width="200" height="150"></applet>*/
public class ColorTest extends Applet{
	public void paint(Graphics g){
		int  i;
		for(i=1; i<8; i++){
			switch(i){
				case 1: g.setColor(Color.red); break;
				case 2: g.setColor(Color.pink); break;
				case 3: g.setColor(Color.orange); break;
				case 4: g.setColor(Color.magenta); break;
				case 5: g.setColor(new Color(0,0,255)); break;
				case 6: g.setColor(new Color(0,0,0)); break;
				case 7: g.setColor(new Color(255,255,255)); break;
			}
			g.drawString("Hello Java", 30, i*20);
		}
	}
}