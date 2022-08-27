import java.applet.*;
import java.awt.*;
/*<applet code="AudioRepeat" width="200" height="100"></applet>*/
public class AudioRepeat extends Applet{
	AudioClip joy;
	public void init(){
		joy = getAudioClip(getCodeBase(), "joy.au");
	}
	public void start(){
		joy.loop();
	}
	public void stop(){
		joy.stop();
	}
	public void paint(Graphics g){
		g.drawString("Audio Clip played", 30, 30);
	}
}