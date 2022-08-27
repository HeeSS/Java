import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeCheckbox" width="300" height="150"></applet>*/
public class MakeCheckbox extends Applet implements ItemListener{
	int x1,x2,x3,y1,y2,y3;
	String msg1="", msg2="", msg3="";
	Checkbox ko, eng, math;
	public void init(){
		ko=new Checkbox("국어");
		add(ko);
		ko.addItemListener(this);
		eng=new Checkbox("영어");
		add(eng);
		eng.addItemListener(this);
		math=new Checkbox("수학");
		add(math);
		math.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent e){
		if(ko.getState()==true){
			msg1="국어 선택"; x1=10; y1=100; }
		else	msg1="";
		if(eng.getState()==true){
			msg2="영어 선택"; x2=10; y2=120; }
		else	msg2="";
		if(math.getState()==true){
			msg3="수학 선택"; x3=10; y3=140; }
		else	msg3="";
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg1, x1, y1);
		g.drawString(msg2, x2, y2);
		g.drawString(msg3, x3, y3);
	}
}