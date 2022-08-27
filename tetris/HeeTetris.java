//package tetris;
//1��: �̹��� ���ε�o,��ϱ���o,�ʱ���o,����ȭ��o,����o,��ư+��ưŬ���� ���� �׼�o
//2��: Ű���� �Է¿� ���� �׼�o,����ó��o,���ó��o,������ ���� �ӵ� ó��o,���ӿ��� ó��o,������ ���� ��������o,�����̽���o,�������o,�����o,�ڵ�����o
//�����ϼ���
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;

/*<applet code="HeeTetris" width="600" height="630">
 <param name="image" value="blocks.jpg">
 </applet>*/

public class HeeTetris extends Applet implements Runnable, ActionListener, KeyListener{
	double version=4.0;
	
	Thread clock;
	
	Image off; //�޸� ���� ����ȭ��
	Graphics offG; //����ȭ�鿡 ����ϱ� ���� �׷��� ���ؽ�Ʈ
	
	Image blocks;
	
	Random r=new Random();
	
	int turn=0;
	int[] turnX=new int[4];
	int[] turnY=new int[4];
	
	int speed=0;
	int topScore=0;
	int score=0;
	int level=0; //(0:easy, 1:medium, 2:hard)
	boolean gameOn=false;
	boolean helpOn=false;
	boolean gameOver=false;
	int warning=0;
	String[] Level = {"easy","medium","hard"};
	String[] Warning = {"\"Press Button\"", "-------Help-------", " ", " \"Wow Great!\"", " \"Game Over\"", " \"Chance!\"", " \"No chance\""};
	
	int blockShape; //��� ��� 7���� �� ?(0~6), 7:chance block
	Color blockColor; //���� blockShape�� ��� color
	int[] X=new int[4]; //���� ����� x��ǥ
	int[] Y=new int[4]; //���� ����� y��ǥ
	
	boolean[][] map=new boolean[20][14]; //��[����(X)][����(Y)]�� �ۼ���
	Color[][] mapColor=new Color[20][14];
	
	Button helpButton, startButton, exitButton;
	Button easyButton, mediumButton, hardButton; //����
	Panel buttonPanel;
	
	Font normalFont = new Font("Arial", Font.BOLD, 12);
	Font topScoreFont = new Font("Arial", Font.BOLD, 18);
	Font scoreFont = new Font("Arial", Font.BOLD, 20);
	Font warningFont = new Font("Arial", Font.BOLD, 25);
	
	public void init(){
		off=createImage(301*2, 211*2); //��: ����20ĭ(20*15+1),����14ĭ(14*15+1) *2(���ũ��30����Ű��)
		offG=off.getGraphics();
		offG.setColor(Color.gray);
		offG.fillRect(0, 0, 350*2, 350*2);
		
		//�̹��� �ε�
		blocks=getImage(getCodeBase(), getParameter("image"));

		setLayout(null);//��ġ,ũ�� ���� �����ϵ���
		
		buttonPanel=new Panel();
		buttonPanel.setLayout(new GridLayout(2,3));
		buttonPanel.setBounds(210,80,180,60);
		add(buttonPanel);
		
		helpButton=new Button("HELP");
		helpButton.setForeground(Color.white);
		helpButton.setBackground(Color.blue);
		startButton=new Button("START");
		startButton.setForeground(Color.white);
		startButton.setBackground(Color.blue);
		exitButton=new Button("EXIT");
		exitButton.setForeground(Color.white);
		exitButton.setBackground(Color.blue);
		easyButton=new Button("EASY");
		easyButton.setBackground(Color.green);
		mediumButton=new Button("MEDIUM");
		mediumButton.setBackground(Color.green);
		hardButton=new Button("HARD");
		hardButton.setBackground(Color.green);
		
		buttonPanel.add(helpButton);
		buttonPanel.add(startButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(easyButton);
		buttonPanel.add(mediumButton);
		buttonPanel.add(hardButton);
		
		helpButton.addActionListener(this);
		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		easyButton.addActionListener(this);
		mediumButton.addActionListener(this);
		hardButton.addActionListener(this);
		
		helpButton.setEnabled(true);
		startButton.setEnabled(true);
		exitButton.setEnabled(false);
		easyButton.setEnabled(false);
		mediumButton.setEnabled(true);
		hardButton.setEnabled(true);
		
		addKeyListener(this);
		
		setNextBlock();
		initMap();
		drawGrid();
	}
	
	public void start(){
		if(clock==null){
			clock=new Thread(this);
			clock.start(); //�ð� ����
		}
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	public void stop(){
		if((clock!=null)&&(clock.isAlive())){
			clock=null;
		}
	}
	
	public void run(){
		while(true){
			try{
				clock.sleep(1200-speed*5);
			}catch(InterruptedException ie){}
			
			if(gameOn && (!gameOver)){//������ �������̸�
				removeBlock();
				if(block_can_drop()){
					for(int i=0; i<4; i++){
						Y[i]+=1;
					}
				}
				else{//����� �ٴڿ� �������
					setMap();
					setNextBlock();
					lineCheck(); //���������� �ִ��� Ȯ���ϰ� ó��
					gameOverCheck();
					if(gameOver) play(getCodeBase(),"death.au");
				}
				setMap();
				drawMap();
				drawGrid();
			}
			
			repaint();
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(off,  0,  210, this);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 210);
		g.drawImage(blocks, 20, 0, this);
		g.drawImage(blocks, 410, 0, this);
		
		g.setColor(Color.darkGray);
		g.drawString("ver "+version, 283, 160);
		g.drawString("Hansung University", 245, 175);
		g.drawString("tjgmltjs22@naver.com", 240, 190);
		
		g.setFont(scoreFont);
		g.setColor(Color.yellow);
		g.drawString("WELCOME", 250, 30);
		g.drawString("HEE TETRIS!", 240, 60);
		
		g.setFont(topScoreFont);
		g.setColor(Color.white);
		g.drawString("TopScore: "+topScore, 20, 105);
		
		g.setFont(scoreFont);
		g.setColor(Color.red);
		g.drawString("Score: "+score, 20, 135);
		
		g.setColor(new Color(60,100,240));
		g.drawString("Level: "+Level[level], 20, 175);
		
		g.setFont(warningFont);
		g.setColor(Color.pink);
		g.drawString(Warning[warning], 410, 135);
	}
	
	public void actionPerformed(ActionEvent e){
		String typed=e.getActionCommand();
		if(typed.equals("HELP")){
			helpOn=true;
			warning=1;
			drawHelp();
			helpButton.setEnabled(false);
			startButton.setEnabled(false);
			exitButton.setEnabled(true);
			easyButton.setEnabled(false);
			mediumButton.setEnabled(false);
			hardButton.setEnabled(false);
			repaint();
		}
		if(typed.equals("START")){
			switch(level){
			case 1://medium
				initMedium(); break;
			case 2://hard
				initHard(); break;
			}
			
			gameOn=true;
			gameOver=false;
			score=0;
			warning=2;
			helpButton.setEnabled(false);
			startButton.setEnabled(false);
			exitButton.setEnabled(true);
			easyButton.setEnabled(false);
			mediumButton.setEnabled(false);
			hardButton.setEnabled(false);
			repaint();
		}
		if(typed.equals("EXIT")){
			if(helpOn){
				helpOn=false;
				warning=0;
				offG.setColor(Color.gray);
				offG.fillRect(0, 0, 350*2, 350*2);
				drawGrid();
				helpButton.setEnabled(true);
				startButton.setEnabled(true);
				exitButton.setEnabled(false);
				switch(level){
				case 0: easyButton.setEnabled(false);
					mediumButton.setEnabled(true);
					hardButton.setEnabled(true);
					break;
				case 1: easyButton.setEnabled(true);
					mediumButton.setEnabled(false);
					hardButton.setEnabled(true);
					break;
				case 2: easyButton.setEnabled(true);
					mediumButton.setEnabled(true);
					hardButton.setEnabled(false);
					break;
				}
				repaint();
			}
			else if(gameOn){
				gameOn=false;
				score=0;
				speed=0;
				warning=0;
				level=0;
				offG.setColor(Color.gray);
				offG.fillRect(0, 0, 350*2, 350*2);
				setNextBlock();
				initMap();
				drawGrid();
				helpButton.setEnabled(true);
				startButton.setEnabled(true);
				exitButton.setEnabled(false);
				easyButton.setEnabled(false);
				mediumButton.setEnabled(true);
				hardButton.setEnabled(true);
				repaint();
			}
		}
		if(typed.equals("EASY")){
			level=0;
			easyButton.setEnabled(false);
			mediumButton.setEnabled(true);
			hardButton.setEnabled(true);
			repaint();
		}
		if(typed.equals("MEDIUM")){
			level=1;
			easyButton.setEnabled(true);
			mediumButton.setEnabled(false);
			hardButton.setEnabled(true);
			repaint();
		}
		if(typed.equals("HARD")){
			level=2;
			easyButton.setEnabled(true);
			mediumButton.setEnabled(true);
			hardButton.setEnabled(false);
			repaint();
		}
		this.requestFocus();
	}
	
	public void keyPressed(KeyEvent e){
		int keyTyped=(int)e.getKeyCode();
		
		if(keyTyped==KeyEvent.VK_C){
			if(score<200){
				warning=6;
			}
			else{
				removeBlock();
				setChanceBlock();
				score-=200;
			}
		}
		if(keyTyped==KeyEvent.VK_SPACE){
			removeBlock(); //�ڡڡڡڡ����� �ڽ� ��ġ�� �����ϰ� �����ؾ� �ϹǷ�!!!
			boolean sound=false;
			while(block_can_drop()){
				sound=true;
				for(int i=0; i<4; i++){
					Y[i]=Y[i]+1;
				}
			}
			if(sound) play(getCodeBase(),"TYPEKEY.au");
		}
		if(keyTyped==KeyEvent.VK_UP){
			removeBlock();
			if(block_can_turn()){
				for(int i=0; i<4; i++){
					X[i]=turnX[i]; Y[i]=turnY[i];
				}
				
				if(turn<3) turn++;
				else turn=0;
			}
		}
		if(keyTyped==KeyEvent.VK_DOWN){
			removeBlock(); //�ڡڡڡڡ����� �ڽ� ��ġ�� �����ϰ� �����ؾ� �ϹǷ�!!!
			if(block_can_drop()){
				for(int i=0; i<4; i++){
					Y[i]=Y[i]+1;
				}
			}
		}
		if(keyTyped==KeyEvent.VK_RIGHT){
			removeBlock();
			if(block_can_move(1)){
				for(int i=0; i<4; i++){
					X[i]=X[i]+1;
				}
			}
		}
		if(keyTyped==KeyEvent.VK_LEFT){
			removeBlock();
			if(block_can_move(-1)){
				for(int i=0; i<4; i++){
					X[i]=X[i]-1;
				}
			}
		}
		setMap();
		drawMap();
		drawGrid();
		repaint();
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	
	//#####��� ó��#####
	public void setNextBlock(){
		blockShape=Math.abs(r.nextInt()%7);
		setBlockShape(blockShape);
		turn=0;
		warning=2;
	}
	public void setChanceBlock(){
		blockShape=7;
		setBlockShape(blockShape);
		turn=0;
		warning=5;
	}
	
	public void setBlockShape(int b){//b���� blockShape���� ���� ��
		//����� ���� ���� �����ϴ� �޼ҵ�
		switch(b){
		case 0: blockColor=Color.orange; //���1
				X[0]=0; Y[0]=0;
				X[1]=1; Y[1]=0;
				X[2]=0; Y[2]=1;
				X[3]=1; Y[3]=1;
				break;
		case 1: blockColor=Color.red; //���2
				X[0]=2; Y[0]=0;
				X[1]=0; Y[1]=1;
				X[2]=1; Y[2]=1;
				X[3]=2; Y[3]=1;
				break;
		case 2: blockColor=Color.blue; //���3
				X[0]=0; Y[0]=0;
				X[1]=1; Y[1]=0;
				X[2]=2; Y[2]=0;
				X[3]=3; Y[3]=0;
				break;
		case 3: blockColor=Color.green; //���4
				X[0]=1; Y[0]=0;
				X[1]=0; Y[1]=1;
				X[2]=1; Y[2]=1;
				X[3]=0; Y[3]=2;
				break;
		case 4: blockColor=Color.yellow; //���5
				X[0]=1; Y[0]=0;
				X[1]=0; Y[1]=1;
				X[2]=1; Y[2]=1;
				X[3]=2; Y[3]=1;
				break;
		case 5: blockColor=Color.cyan; //���6
				X[0]=0; Y[0]=0;
				X[1]=0; Y[1]=1;
				X[2]=1; Y[2]=1;
				X[3]=1; Y[3]=2;
				break;
		case 6: blockColor=Color.magenta; //���7
				X[0]=0; Y[0]=0;
				X[1]=0; Y[1]=1;
				X[2]=1; Y[2]=1;
				X[3]=2; Y[3]=1;
				break;
		case 7: blockColor=Color.white; //chance block
				X[0]=0; Y[0]=0;
				X[1]=0; Y[1]=0;
				X[2]=0; Y[2]=0;
				X[3]=0; Y[3]=0;
		break;
		}
		for(int i=0; i<4; i++){
			X[i]+=8;
		}
	}
	
	public void removeBlock(){
		for(int i=0; i<4; i++){
			map[X[i]][Y[i]]=false;
			mapColor[X[i]][Y[i]]=Color.gray;
		}
	}
	
	public boolean block_can_turn(){
		switch(blockShape){
		case 0: case 7:
			for(int i=0; i<4; i++){
				turnX[i]=X[i]; turnY[i]=Y[i];
			}
			break; //���簢�����(���1), chance block
		case 1://(���2)
			switch(turn){
			case 0:
				turnX[0]=X[0]-2; turnY[0]=Y[0];
				turnX[1]=X[1]; turnY[1]=Y[1];
				turnX[2]=X[2]-1; turnY[2]=Y[2]+1;
				turnX[3]=X[3]-1; turnY[3]=Y[3]+1;
				break;
			case 1:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]+1; turnY[1]=Y[1]-1;
				turnX[2]=X[2]+2; turnY[2]=Y[2]-2;
				turnX[3]=X[3]-1; turnY[3]=Y[3]-1;
				break;
			case 2:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]; turnY[1]=Y[1];
				turnX[2]=X[2]-1; turnY[2]=Y[2]+1;
				turnX[3]=X[3]+1; turnY[3]=Y[3]+1;
				break;
			case 3:
				turnX[0]=X[0]+2; turnY[0]=Y[0];
				turnX[1]=X[1]-1; turnY[1]=Y[1]+1;
				turnX[2]=X[2]; turnY[2]=Y[2];
				turnX[3]=X[3]+1; turnY[3]=Y[3]-1;
				break;
			}
			break;
		case 2://(���3)
			switch(turn){
			case 0: case 2:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]-1; turnY[1]=Y[1]+1;
				turnX[2]=X[2]-2; turnY[2]=Y[2]+2;
				turnX[3]=X[3]-3; turnY[3]=Y[3]+3;
				break;
			case 1: case 3:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]+1; turnY[1]=Y[1]-1;
				turnX[2]=X[2]+2; turnY[2]=Y[2]-2;
				turnX[3]=X[3]+3; turnY[3]=Y[3]-3;
				break;
			}
			break;
		case 3://(���4)
			switch(turn){
			case 0: case 2:
				turnX[0]=X[0]-1; turnY[0]=Y[0];
				turnX[1]=X[1]+1; turnY[1]=Y[1]-1;
				turnX[2]=X[2]; turnY[2]=Y[2];
				turnX[3]=X[3]+2; turnY[3]=Y[3]-1;
				break;
			case 1: case 3:
				turnX[0]=X[0]+1; turnY[0]=Y[0];
				turnX[1]=X[1]-1; turnY[1]=Y[1]+1;
				turnX[2]=X[2]; turnY[2]=Y[2];
				turnX[3]=X[3]-2; turnY[3]=Y[3]+1;
				break;
			}
			break;
		case 4://(���5)
			switch(turn){
			case 0:
				turnX[0]=X[0]-1; turnY[0]=Y[0];
				turnX[1]=X[1]; turnY[1]=Y[1];
				turnX[2]=X[2]; turnY[2]=Y[2];
				turnX[3]=X[3]-2; turnY[3]=Y[3]+1;
				break;
			case 1:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]+1; turnY[1]=Y[1]-1;
				turnX[2]=X[2]+1; turnY[2]=Y[2]-1;
				turnX[3]=X[3]+1; turnY[3]=Y[3]-1;
				break;
			case 2:
				turnX[0]=X[0]+1; turnY[0]=Y[0];
				turnX[1]=X[1]-1; turnY[1]=Y[1]+1;
				turnX[2]=X[2]-1; turnY[2]=Y[2]+1;
				turnX[3]=X[3]; turnY[3]=Y[3]+1;
				break;
			case 3:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]; turnY[1]=Y[1];
				turnX[2]=X[2]; turnY[2]=Y[2];
				turnX[3]=X[3]+1; turnY[3]=Y[3]-1;
				break;
			}
			break;
		case 5://(���6)
			switch(turn){
			case 0: case 2:
				turnX[0]=X[0]+1; turnY[0]=Y[0];
				turnX[1]=X[1]+2; turnY[1]=Y[1]-1;
				turnX[2]=X[2]-1; turnY[2]=Y[2];
				turnX[3]=X[3]; turnY[3]=Y[3]-1;
				break;
			case 1: case 3:
				turnX[0]=X[0]-1; turnY[0]=Y[0];
				turnX[1]=X[1]-2; turnY[1]=Y[1]+1;
				turnX[2]=X[2]+1; turnY[2]=Y[2];
				turnX[3]=X[3]; turnY[3]=Y[3]+1;
				break;
			}
			break;
		case 6://(���7)
			switch(turn){
			case 0:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]+1; turnY[1]=Y[1]-1;
				turnX[2]=X[2]-1; turnY[2]=Y[2];
				turnX[3]=X[3]-2; turnY[3]=Y[3]+1;
				break;
			case 1:
				turnX[0]=X[0]; turnY[0]=Y[0];
				turnX[1]=X[1]; turnY[1]=Y[1];
				turnX[2]=X[2]+2; turnY[2]=Y[2]-1;
				turnX[3]=X[3]+2; turnY[3]=Y[3]-1;
				break;
			case 2:
				turnX[0]=X[0]+1; turnY[0]=Y[0];
				turnX[1]=X[1]; turnY[1]=Y[1]+1;
				turnX[2]=X[2]-2; turnY[2]=Y[2]+2;
				turnX[3]=X[3]-1; turnY[3]=Y[3]+1;
				break;
			case 3:
				turnX[0]=X[0]-1; turnY[0]=Y[0];
				turnX[1]=X[1]-1; turnY[1]=Y[1];
				turnX[2]=X[2]+1; turnY[2]=Y[2]-1;
				turnX[3]=X[3]+1; turnY[3]=Y[3]-1;
				break;
			}
			break;
		}
		
		for(int i=0; i<4; i++){
			if((turnX[i]<0)||(turnX[i]>=20)||(turnY[i]<0)||(turnY[i]>=14)) return false;
			else{
				if(map[turnX[i]][turnY[i]]) return false;
			}
		}
		return true;		
	}
	
	public boolean block_can_drop(){
		removeBlock(); //�ڡڡڡڡ����� �ڽ� ��ġ�� �����ϰ� �����ؾ� �ϹǷ�!!!
		for(int i=0; i<4; i++){
			if((Y[i]+1)==14) return false; //����� �ٴڿ� ��Ұų�
			else{
				if(map[X[i]][Y[i]+1]) return false; //�Ʒ��� �ٸ� ����� �ְų�
			}
		}
		return true;
	}
	
	public boolean block_can_move(int n){
		for(int i=0; i<4; i++){
			if(((X[i]+n)<0)||((X[i]+n)>=20)) return false;
			else{
				if(map[X[i]+n][Y[i]]) return false;
			}
		}
		return true;
	}
	
	
	//#####�� ó��#####
	public void initMap(){
		for(int x=0; x<20; x++){
			for(int y=0; y<14; y++){
				map[x][y]=false;
			}
		}
	}
	
	public void setMap(){
		for(int i=0; i<4; i++){
			map[X[i]][Y[i]]=true;
			mapColor[X[i]][Y[i]]=blockColor;
		}
	}
	
	
	//#####draw___#####
	public void drawMap(){
		for(int x=0; x<20; x++){
			for(int y=0; y<14; y++){
				if(map[x][y]){ //������ġ�� ����� �ִٸ�
					offG.setColor(mapColor[x][y]);
					offG.fillRect(x*30, y*30, 30, 30);
				}
				else{//���ٸ�
					offG.setColor(Color.gray);
					offG.fillRect(x*30, y*30, 30, 30);
				}
			}
		}
	}
	
	public void drawGrid(){
		offG.setColor(Color.darkGray);
		for(int x=0; x<20; x++){
			for(int y=0; y<14; y++){
				offG.drawRect(x*30,  y*30, 30, 30);
			}
		}
	}
	
	public void drawHelp(){
		offG.setColor(Color.gray);
		offG.fillRect(0, 0, 350*2, 350*2);
		offG.setColor(Color.white);
		offG.setFont(topScoreFont);
		offG.drawString("Press Key ��(up): Turn block", 10, 30);
		offG.drawString("Press Key ��(down): Move down", 10, 60);
		offG.drawString("Press Key ��(left): Move left", 10, 90);
		offG.drawString("Press Key ��(right): Move right", 10, 120);
		offG.drawString("Press Key space bar: Move to the bottom", 10, 150);
		offG.setColor(Color.pink);
		offG.drawString("Press Key C: Use '1*1 block' chance (200 score = 1 chance)", 10, 180);
		
		offG.setColor(Color.darkGray);
		offG.drawString("Easy Level: Basic", 10, 230);
		offG.drawString("Medium Level: Handicap start", 10, 260);
		offG.drawString("Hard Level: Greater handicap than medium level", 10, 290);
		
		offG.setColor(Color.orange);
		offG.drawString(">> The more score you get, the faster you'll play.", 10, 340);
		offG.drawString(">> The faster you play, the more score you'll get.", 10, 370);
		offG.drawString(">> Everytime you use 1 chance, it'll reduce score(200).", 10, 400);
	}
	
	
	//#####line ó��#####
	public void lineCheck(){
		for(int y=0; y<14; y++){
			if(line_can_delete(y)){ //y���� ������ ��� �� á�ٸ� �� ���� & speed up & score up
				warning=3;
				deleteLine(y); y--;
				play(getCodeBase(),"XYLO_UP.au");
				if(speed<240) speed+=10;
				score+=speed; //�ӵ��� ��������(������ ��������) ȹ�������� ���� Ŀ��
			}
		}
	}
	
	public boolean line_can_delete(int y){		
		for(int x=0; x<20; x++){
			if(map[x][y]==false) return false; //������ ������ false
		}
		return true;
	}
	
	public void deleteLine(int y){
		for(int delY=y; delY>0; delY--){ //1~y���� �ڸ��� 1~(y-1)���� �ִ´�
			for(int x=0; x<20; x++){
				map[x][delY]=map[x][delY-1];
				mapColor[x][delY]=mapColor[x][delY-1];
			}
		}
		for(int x=0; x<20; x++){ //0���� �������� �ʱ�ȭ
			map[x][0]=false;
			mapColor[x][0]=Color.gray;
		}
	}
	
	
	//#####����&���ӿ��� ó��#####
	public void initMedium(){
		int x;
		
		for(int i=0; i<4; i++){
			do{
				x = Math.abs(r.nextInt()%20);
			}while(map[x][13]);
			
			for(int y=13; y>=13-i; y--){
				map[x][y]=true;
				mapColor[x][y]=Color.darkGray;
			}
		}
	}
	
	public void initHard(){
		int x, y;
		
		for(int i=0; i<4; i++){
			do{
				x = Math.abs(r.nextInt()%20);
				y = Math.abs(r.nextInt()%9)+5;
			}while(map[x][y]);
			
			map[x][y]=true;
			mapColor[x][y]=Color.darkGray;
		}
	}
	
	public void gameOverCheck(){
		for(int i=0; i<4; i++){
			//nextBlock�� ��Ÿ�� X[], Y[]��ǥ�� �̹� ����� ������
			if(map[X[i]][Y[i]]){
				gameOver=true;
				warning=4;
				if(topScore<score)topScore=score;
			}
		}
	}
}

