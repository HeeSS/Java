import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="MakeTextField" width="200" height="200"></applet>*/
public class MakeTextField extends Applet implements ActionListener{
	String s1, s2, msg;
	Label nameLabel, passwdLabel, message;
	TextField name, passwd;
	public void init(){
		message=new Label("�̸��� ��ȣ�� �Է��Ͻÿ�.");
		nameLabel=new Label("�̸�: ", Label.RIGHT);
		passwdLabel=new Label("��ȣ: ", Label.RIGHT);
		name=new TextField(15);
		passwd=new TextField(12);
		add(message); add(nameLabel); add(name); add(passwdLabel); add(passwd);
		passwd.setEchoChar('*');
		name.addActionListener(this);
		passwd.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		s1=name.getText();
		s2=passwd.getText();
		msg=s1+","+s2;
		repaint();
	}
	public void paint(Graphics g){
		g.drawString(msg, 10, 150);
	}
}