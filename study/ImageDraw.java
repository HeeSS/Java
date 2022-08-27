import java.applet.*;
import java.awt.*;
/*<applet code="ImageDraw" width="400" height="200">
<param name="img1" value="hansung.jpg"><param name="img2" value="hansung2.jpg"></applet>*/
public class ImageDraw extends Applet{
	Image mark1, mark2;
	public void init(){
		mark1=getImage(getCodeBase(), getParameter("img1"));
		mark2=getImage(getCodeBase(), getParameter("img2"));
	}
	public void paint(Graphics g){
		g.drawImage(mark1, 10, 10, this);
		g.drawImage(mark2, 150, 10, this);
	}
}