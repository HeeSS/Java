import java.awt.*;
import java.applet.Applet;
/*<applet code="MakeFlowLayout" width="400" height="200"></applet>*/
public class MakeFlowLayout extends Applet{
	Button a,b,c,d,e,f,g;
	public void init(){
		a=new Button("Button1");
		b=new Button("Button2");
		c=new Button("Button3");
		d=new Button("Button4");
		e=new Button("Button5");
		f=new Button("Button6");
		g=new Button("Button7");
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		add(a); add(b); add(c); add(d); add(e); add(f); add(g);
	}
}