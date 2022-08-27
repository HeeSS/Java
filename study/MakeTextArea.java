import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeTextArea" width="400" height="200"></applet>*/
public class MakeTextArea extends Applet{
	String contents;
	TextArea area;
	public void init(){
		area=new TextArea(10, 20);
		add(area);
		contents="Java\n" + "C++\n" + "PASCAL\n";
		area.setText(contents);
		area.append("Hansung\n");
		area.insert("Seoul\n", 9);
	}
}