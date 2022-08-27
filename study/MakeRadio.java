import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeRadio" width="300" height="150"></applet>*/
public class MakeRadio extends Applet implements ItemListener{
	String msg="";
	Checkbox ko, eng, math;
	CheckboxGroup myGroup;
	public void init(){
		myGroup=new CheckboxGroup();
		ko=new Checkbox("국어",myGroup,true);
		add(ko);
		ko.addItemListener(this);
		eng=new Checkbox("영어",myGroup,false);
		add(eng);
		eng.addItemListener(this);
		math=new Checkbox("수학",myGroup,false);
		add(math);
		math.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent e){
		if(ko.getState()==true)	msg="국어 선택"; 
		else if(eng.getState()==true)	msg="영어 선택";  
		else if(math.getState()==true)	msg="수학 선택"; 
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 10, 100);
	}
}