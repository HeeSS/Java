import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeButton" width="300" height="150"></applet>*/
public class MakeButton extends Applet implements ActionListener{
	String msg="";
	Button ko, eng, math;
	public void init(){
		ko=new Button("����");
		add(ko);
		ko.addActionListener(this);
		eng=new Button("����");
		add(eng);
		eng.addActionListener(this);
		math=new Button("����");
		add(math);
		math.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("����")) msg="���� ����";
		else if(cmd.equals("����")) msg="���� ����";
		else msg="���� ����";
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 0, 20);
	}
}