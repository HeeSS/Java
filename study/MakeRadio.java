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
		ko=new Checkbox("����",myGroup,true);
		add(ko);
		ko.addItemListener(this);
		eng=new Checkbox("����",myGroup,false);
		add(eng);
		eng.addItemListener(this);
		math=new Checkbox("����",myGroup,false);
		add(math);
		math.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent e){
		if(ko.getState()==true)	msg="���� ����"; 
		else if(eng.getState()==true)	msg="���� ����";  
		else if(math.getState()==true)	msg="���� ����"; 
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 10, 100);
	}
}