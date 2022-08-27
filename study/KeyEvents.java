import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/* <applet code="KeyEvents" width="300" height="100"></applet>*/
public class KeyEvents extends Applet {
	String msg="";
	int x=10, y=20;
	public void init(){
		addKeyListener(new MyKey());
		requestFocus();
	}
	class MyKey extends KeyAdapter{
		public void keyTyped(KeyEvent e){
			msg+=e.getKeyChar();
			repaint();
		}
	}
	public void paint(Graphics g){
		g.drawString(msg, x, y);
	}
}