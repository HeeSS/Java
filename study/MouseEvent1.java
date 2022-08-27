import java.awt.*;
import java.applet.*;
/*
<applet code="MouseEvent1" width="300" height="100">
</applet>
*/
public class MouseEvent1 extends Applet{
        String msg="";
        int x = 0, y = 0;
        public void init(){
                addMouseListener(new MyMouse());
                addMouseMotionListener(new MyMotion());
        }
}
class MyMotion extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e){
                x = e.getX();
                y = e.getY();
                msg = "*";
                showStatus("(" + x + ", " + y + " : )" + "에서 드래그");
                repaint();
        }
}
class MyMouse extends MouseAdapter{
	public void mouseClicked(MouseEvent e){
               		 x = 0;
               		 y = 10;
              		  msg = "마우스 클릭";
              		  repaint();
     	   }
	public void paint(Graphics g){
            		    g.drawString(msg, x, y);
      	  }
}