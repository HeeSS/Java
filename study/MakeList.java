import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeList" width="300" height="150"></applet>*/
public class MakeList extends Applet implements ItemListener, ActionListener{
	String msg;
	List items;
	int x, y, count;
	public void init(){
		items=new List(3, true);
		items.add("국어"); items.add("영어"); items.add("수학");
		items.select(1); add(items);
		items.addItemListener(this); items.addActionListener(this);
		count=items.getItemCount();
	}
	public void itemStateChanged(ItemEvent e){
		msg="";
		String[] selected = items.getSelectedItems();
		int[] selectedIndex = items.getSelectedIndexes();
		for(int i=0; i<selected.length; i++)
			msg+=selected[i]+"  "+selectedIndex[i]+"  ";
		x=10; y=120;
		repaint();
	}
	public void actionPerformed(ActionEvent e){
		msg="";
		msg=e.getActionCommand();
		msg+=" will be executed.";
		x=10; y=100;
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, x, y);
		g.drawString("(총 개수: " +count + ")", x, y+20);
	}
}