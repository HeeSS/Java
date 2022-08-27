import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeButton" width="300" height="150"></applet>*/
public class MakeButton extends Applet implements ActionListener{
	String msg="";
	Button ko, eng, math;
	public void init(){
		ko=new Button("국어");
		add(ko);
		ko.addActionListener(this);
		eng=new Button("영어");
		add(eng);
		eng.addActionListener(this);
		math=new Button("수학");
		add(math);
		math.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("국어")) msg="국어 선택";
		else if(cmd.equals("영어")) msg="영어 선택";
		else msg="수학 선택";
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 0, 20);
	}
}