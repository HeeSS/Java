import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeChoice" width="300" height="150"></applet>*/
public class MakeChoice extends Applet implements ItemListener{
	String msg="";
	Choice item;
	int count;
	public void init(){
		item=new Choice();
		item.add("국어"); item.add("영어"); item.add("수학"); item.add("탐구");
		add(item);
		item.addItemListener(this);
		count=item.getItemCount();
	}
	public void itemStateChanged(ItemEvent e){
		msg=item.getSelectedItem()+item.getSelectedIndex();
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 10, 100);
		g.drawString("항목의 개수는 "+count+" 입니다.", 10, 120);
	}
}