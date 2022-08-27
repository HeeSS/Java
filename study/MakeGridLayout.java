import java.awt.*;
import java.applet.Applet;
/*<applet code="MakeGridLayout" width="400" height="200"></applet>*/
public class MakeGridLayout extends Applet{
	Button a,b,c,d;
	TextArea t;
	public void init(){
		a=new Button("1");
		b=new Button("2");
		c=new Button("3");
		d=new Button("4");
		t=new TextArea();
		t.setText("TextArea");
		setLayout(new GridLayout(2,3,2,2));
		add(a); 
		add(b); 
		add(t);
		add(c); 
		add(d);
	}
}