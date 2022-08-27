//package slotgame;
//���� ��������?
import java.applet.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
/*<applet code=SlotGame09.java width=192 height=260></applet>*/
public class SlotGame09 extends Applet 
 implements Runnable, ActionListener
{ 
  int count=0;
  int money=10000;
  boolean plusM=false;
  Font warningFont = new Font("Arial", Font.BOLD, 25);
  
  Thread clock;

  Image off; // �޸� ���� ����ȭ��
  Graphics offG; // ����ȭ��� �׷��� ���ؽ�Ʈ

  Image slot;
  Image machine;

  Random r;

  int[] loc;   // slot �̹��� ����
  int[] speed; // slot �ӵ�
  int[] hit;   // ������ ��ȣ��
  int slotNum;
  boolean[] stopSlot;
  boolean[] moveSlot;

  Button startButton, stopButton;
  Panel buttonPanel;

  public void init()
  {
    // �޸� �� ����ȭ�� �����
    off= createImage(192, 192);
    offG= off.getGraphics(); //����ȭ�鿡 ����ϱ� ���� �׷��� ���ؽ�Ʈ
    offG.setColor(Color.white);
    offG.fillRect(0,0,192,192);

    // �̹��� �ε�
    MediaTracker tracker= new MediaTracker(this);
    slot= getImage(getCodeBase(), "slot.gif");
    tracker.addImage(slot,0); //�̹��� ���
    machine= getImage(getCodeBase(), "machine.gif");
    tracker.addImage(machine,0);
    try{ 
      tracker.waitForAll(); 
    }catch(InterruptedException ie){}

    while((tracker.statusAll(true) & MediaTracker.COMPLETE)==0){}

    // GUI
    setLayout(new BorderLayout());
    buttonPanel= new Panel();
    startButton= new Button("START");
    startButton.addActionListener(this);
    buttonPanel.add(startButton);
    stopButton= new Button("STOP");
    stopButton.addActionListener(this);
    buttonPanel.add(stopButton);
    add("South", buttonPanel);

    loc= new int[3];
    speed= new int[3];
    hit= new int[3];
    stopSlot= new boolean[3];
    moveSlot= new boolean[3];

    r= new Random();

    for(int i=0;i<3;i++){
      loc[i]= Math.abs(r.nextInt() % 7)* 64;
      //speed[i]=  Math.abs(r.nextInt() % 7) * 8 + 8;
speed[0]=34; speed[1]=14; speed[2]=10;
      stopSlot[i]= true;
      moveSlot[i]= false;
    }

    slotNum= 0;
  }

  public void start()
  {
    if(clock==null){
      clock= new Thread(this);
      clock.start();  // �ð� ����
    }
  }

  public void paint(Graphics g)
  {
    // ����ȭ���� ����ȭ�鿡 ���
    g.drawImage(off, 0, 0, this); 
    g.setFont(warningFont);
    g.setColor(Color.white);
    g.fillRect(0, 193, 192, 30);
    g.setColor(Color.BLACK);
    g.drawString(money+" won", 5, 220);
  }

  public void update(Graphics g)
  {
    paint(g);
  }

  public void run()
  {
    while(true)
    {
      try{
        clock.sleep(30);
        count++;
      }catch(InterruptedException ie){}


      offG.setColor(Color.white);
      offG.fillRect(0,0,192,192);

      drawSlot();

      offG.drawImage(machine, 0, 0, this);
      
      repaint(); // paint() ȣ��
    }
  }

  public void drawSlot()
  {
    for(int i=0; i<3; i++){
      if(moveSlot[i]){
        if(loc[i]<432){
          loc[i]+= speed[i];
        }else{
          loc[i]= 0;
        }
      }
      if(stopSlot[i]){
    	if(i==0){
    		if((loc[i]/64)==hit[i]){
  	          loc[i]= loc[i]/64*64;
  	          moveSlot[i]= false;
  	          //count=i;
  	        }
    	}
        else if(i==1){
        	if(moveSlot[0]==false){
        		if((loc[i]/64)==hit[i]){
        	          loc[i]= loc[i]/64*64;
        	          moveSlot[i]= false;
        	          //count=i;
        	    }
        	}
        }
        else{
        	if(moveSlot[0]==false&&moveSlot[1]==false){
        		if((loc[i]/64)==hit[i]){
        	          loc[i]= loc[i]/64*64;
        	          moveSlot[i]= false;
        	          //count=i;
        	          
        	          //111~777���ñ� �߰�
        	          if(plusM&&hit[0]==hit[1]&&hit[1]==hit[2]){
        	        	  if(hit[0]==6){ //111: 500��
        	        		  money+=500*1;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==0){ //222: 1000��
        	        		  money+=500*2;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==1){ //333: 2000��
        	        		  money+=500*4;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==2){ //444: 4000��
        	        		  money+=500*8;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==3){ //555: 5000��
        	        		  money+=500*10;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==4){ //666: 7500��
        	        		  money+=500*15;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==5){ //777: 10000��
        	        		  money+=500*20;
        	        		  plusM=false;
        	        	  }
        	          }
        	          
        	          if(money<500){
        	        	  startButton.setEnabled(false);
        	        	  stopButton.setEnabled(false);
        	          }
        	    }
        	}
        }
      }

      if(loc[i]<320){
        offG.drawImage(slot, i*64, 0-loc[i], this);
      }else{
        offG.drawImage(slot, i*64, 0-loc[i], this);
        offG.drawImage(slot, i*64, 448-loc[i], this);
      }
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==startButton){
      stopSlot[0]= stopSlot[1]= stopSlot[2]= false;
      moveSlot[0]= moveSlot[1]= moveSlot[2]= true;
      slotNum= 0;
count=0;
      money-=500; //start �� ������ 500���� ����
    }else if(e.getSource()==stopButton){
int o=0;
      plusM=true;	
      count=0;	
      slotNum=0;
      hit[slotNum]= Math.abs(r.nextInt() % 7);
      while(count%1800000000!=0){o++;}count=0;o=0;
      stopSlot[slotNum]= true;
      
    //hit[1]=hit[0]; hit[2]=hit[0];
      slotNum=1;
      hit[slotNum]= Math.abs(r.nextInt() % 7);
      while(count%1800000000!=0){o++;}count=0;o=0;
      stopSlot[slotNum]= true;
      
      slotNum=2;
      hit[slotNum]= Math.abs(r.nextInt() % 7);
      while(count%1800000000!=0){o++;}count=0;o=0;
      stopSlot[slotNum]= true;

      
      
//      if(slotNum<2){
//        slotNum++;
//      }else{
//        slotNum= 0;
//      }
    }
  }

  public void stop()
  {
    if((clock!=null)&&(clock.isAlive())){
      clock=null; // �ð� ����(����)
    }
  }

  public void destroy()
  {
    // ���� ��ƾ
  }
} 
