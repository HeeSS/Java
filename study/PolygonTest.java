import java.applet.*;
import java.awt.Graphics;
/*<applet code="PolygonTest" width="500" height="200"></applet>*/
public class PolygonTest extends Applet{
	public void paint(Graphics g){
		int[] x1={50, 50, 100, 150, 150, 100};
		int[] y1={60, 110, 160, 110, 60, 10};
		int n1=x1.length;
		int[] x2={250, 250, 300, 350, 350, 300};
		int[] y2={60, 110, 160, 110, 60, 10};
		int n2=y2.length;

		g.drawPolygon(x1,y1,n1);
		g.fillPolygon(x2,y2,n2);
	}
}