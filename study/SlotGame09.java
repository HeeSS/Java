//package slotgame;
//슬롯 최종수정?
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

  Image off; // 메모리 상의 가상화면
  Graphics offG; // 가상화면용 그래픽 컨텍스트

  Image slot;
  Image machine;

  Random r;

  int[] loc;   // slot 이미지 상태
  int[] speed; // slot 속도
  int[] hit;   // 정지될 번호판
  int slotNum;
  boolean[] stopSlot;
  boolean[] moveSlot;

  Button startButton, stopButton;
  Panel buttonPanel;

  public void init()
  {
    // 메모리 상에 가상화면 만들기
    off= createImage(192, 192);
    offG= off.getGraphics(); //가상화면에 출력하기 위한 그래픽 컨텍스트
    offG.setColor(Color.white);
    offG.fillRect(0,0,192,192);

    // 이미지 로드
    MediaTracker tracker= new MediaTracker(this);
    slot= getImage(getCodeBase(), "slot.gif");
    tracker.addImage(slot,0); //이미지 등록
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
      clock.start();  // 시계 시작
    }
  }

  public void paint(Graphics g)
  {
    // 가상화면을 실제화면에 출력
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
      
      repaint(); // paint() 호출
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
        	          
        	          //111~777배팅금 추가
        	          if(plusM&&hit[0]==hit[1]&&hit[1]==hit[2]){
        	        	  if(hit[0]==6){ //111: 500원
        	        		  money+=500*1;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==0){ //222: 1000원
        	        		  money+=500*2;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==1){ //333: 2000원
        	        		  money+=500*4;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==2){ //444: 4000원
        	        		  money+=500*8;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==3){ //555: 5000원
        	        		  money+=500*10;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==4){ //666: 7500원
        	        		  money+=500*15;
        	        		  plusM=false;
        	        	  }
        	        	  else if(hit[0]==5){ //777: 10000원
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
      money-=500; //start 할 때마다 500원씩 감소
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
      clock=null; // 시계 정지(없앰)
    }
  }

  public void destroy()
  {
    // 종료 루틴
  }
} 
