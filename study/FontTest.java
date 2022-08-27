import java.applet.*;
import java.awt.*;
/*<applet code="FontTest" width="420" height="100"></applet>*/
public class FontTest extends Applet{
	public void paint(Graphics g){
		Font fp=new Font("TimesRoman", Font.PLAIN, 17);
		Font fb=new Font("Helventica", Font.BOLD, 17);
		Font fi=new Font("Courier", Font.ITALIC, 17);
		Font fbi=new Font("Dialog", Font.BOLD+Font.ITALIC, 17);
		g.setFont(fp);	g.setColor(Color.black);
		g.drawString("Font:TimesRoman, Styles:Plain, Color:Black", 10, 22);
		g.setFont(fb);	g.setColor(Color.red);
		g.drawString("Font:Helventica, Styles:Plain, Color:Red", 10, 44);
		g.setFont(fi);	g.setColor(Color.blue);
		g.drawString("Font:Courier, Styles:ITALIC, Color:Blue", 10, 66);
		g.setFont(fbi);	g.setColor(Color.green);
		g.drawString("Font:Dialog, Styles:BOLD+ITALIC, Color:Green", 10, 88);
	}
}