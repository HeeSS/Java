import java.applet.*;
import java.awt.*;

/*<applet code="EventHandling1"  width="200"  height="100"></applet>*/
public class EventHandling1 extends Applet implements ActionListener{
	String msg="";
	Button button1;
	public void init(){
		button1=new Button("OK");
		add(button1);
		button1.addActionListener(this);
	}
	public void actionPerfomed(ActionEvent e){
		String s=e.getActionCommand();
		if(s.equals("OK")){
			msg="버튼을 눌렀습니다.";
		}
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 50, 70);
	}
}