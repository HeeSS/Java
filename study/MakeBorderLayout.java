import java.awt.*;
import java.applet.Applet;
/*<applet code="MakeBorderLayout" width="400" height="200"></applet>*/
public class MakeBorderLayout extends Applet{
	Button a,b,c,d;
	TextArea t;
	public void init(){
		a=new Button("North");
		b=new Button("South");
		c=new Button("East");
		d=new Button("West");
		t=new TextArea();
		t.setText("Center");
		setLayout(new BorderLayout());
		add(a, BorderLayout.NORTH); 
		add(b, BorderLayout.SOUTH); 
		add(c, BorderLayout.EAST); 
		add(d, BorderLayout.WEST);
		add(t, BorderLayout.CENTER);
	}
}