import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;
/* <applet code="HeeHang" width="800"  height="600">
<param name="image" value="hang.jpg">
<param name="image2" value="ball.jpg">
<param name="image3" value="pin.jpg">
<param name="image4" value="bang.jpg"></applet> */
//단어 200개, 철자4~12개
//Quiz
public class HeeHang extends JApplet implements ActionListener{
	double version=3.0;
	int gameOn=0;
	int win=0;
	int loo=0;
	float winPer=0;
	int level=0;
	int guessNum=10;
	int bad=0;
	int check_hint=0;
	
	boolean winSound=false;
	//boolean looSound=false;
	
	String[] words = new String[201]; //사전목록
	int[] word = new int[201]; //게임선택단어 중복여부
	
	String sel_word;
	int word_length; //sel_Word의 철자 수 
	char[] ch_sel_word = new char[13];
	String[] print_word=new String[12]; //화면에 출력할 spell
	
	char[] alphabet={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	JButton[] MyJButton = new JButton[26];
	JButton back = new JButton("BACK");
	JButton begin = new JButton("BEGIN");
	JButton hint = new JButton("HINT");
	JButton easy = new JButton("EASY");
	JButton medium = new JButton("MEDIUM");
	JButton hard = new JButton("HARD");
	JLabel text = new JLabel("Skill level: ", JLabel.LEFT);
	JPanel panIntro = new JPanel();
	JPanel panBegin = new JPanel();
	JPanel panAlphabet = new JPanel(); 
	JPanel panLevel = new JPanel();

	Image sample1;
	Image ball;
	Image pin;
	Image bang;
	//AudioClip winSound;
	//AudioClip looSound;
	
	Font normalFont = new Font("Arial", Font.BOLD, 12);
	Font menuFont = new Font("Arial", Font.BOLD, 17);
	Font warningFont = new Font("Arial", Font.BOLD, 25);
	Font wordCheckFont=new Font("Arial", Font.BOLD, 25);
	
	public void init() {	
		sample1=getImage(getCodeBase(), getParameter("image"));
		ball=getImage(getCodeBase(), getParameter("image2"));
		pin=getImage(getCodeBase(), getParameter("image3"));
		bang=getImage(getCodeBase(), getParameter("image4"));
		//winSound=getAudioClip(getCodeBase(), "win.au");
		//looSound=getAudioClip(getCodeBase(), "loo.au");
		
		GridLayout ALL=new GridLayout(4,1);  // 전체 panel에 대한 layout 설정
		FlowLayout INTRO=new FlowLayout();
		FlowLayout BEGIN=new FlowLayout(FlowLayout.CENTER);  
		GridLayout ALPHABET=new GridLayout(3,9,6,6);
		FlowLayout LEVEL=new FlowLayout(FlowLayout.CENTER);  
		  
		Container root = getContentPane(); //java는 Pane만 쓴다!!!!
		root.setLayout(ALL);
		root.setBackground(Color.white);

		panIntro.setLayout(INTRO);
		panIntro.setBackground(Color.black);
		root.add(panIntro);
		
		panBegin.setLayout(BEGIN);
		panBegin.setBackground(Color.white);
		panBegin.add(back);
		panBegin.add(begin);
		panBegin.add(hint);
		root.add(panBegin);
		back.addActionListener(this);
		begin.addActionListener(this);
		hint.addActionListener(this);
		
		panAlphabet.setLayout(ALPHABET);
		panAlphabet.setBackground(Color.white);
		for(int i=0; i<26; i++){
			MyJButton[i]=new JButton(String.valueOf(alphabet[i]));
			MyJButton[i].setBackground(Color.orange);
			MyJButton[i].setEnabled(false);
			panAlphabet.add(MyJButton[i]);
			MyJButton[i].addActionListener(this);
		}
		root.add(panAlphabet);
		
		panLevel.setLayout(LEVEL);
		panLevel.setBackground(Color.white);
		panLevel.add(text);
		panLevel.add(easy);
		panLevel.add(medium);
		panLevel.add(hard);
		root.add(panLevel);
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);

		setContentPane(root);

		back.setEnabled(false);
		begin.setEnabled(true);
		hint.setEnabled(true);
		easy.setEnabled(false);
		medium.setEnabled(true);
		hard.setEnabled(true);
		
		words[0]="precaution";words[1]="progress";words[2]="propose";words[3]="produce";
		words[4]="protect";words[5]="predict";words[6]="premature";words[7]="preview";
		words[8]="forehead";words[9]="forefather";words[10]="foremost";words[11]="foresee";
		words[12]="postpone";words[13]="postscript";words[14]="income";words[15]="indoor";
		words[16]="infect";words[17]="insight";words[18]="reliable";words[19]="outcome";
		words[20]="outline";words[21]="outlook";words[22]="outstanding";words[23]="outlet";
		words[24]="outdo";words[25]="sensible";words[26]="utmost";words[27]="overcome";
		words[28]="overlook";words[29]="overseas";words[30]="overhead";words[31]="overtake";
		words[32]="overwhelm";words[33]="overthrow";words[34]="overwork";words[35]="careful";
		words[36]="recover";words[37]="recycle";words[38]="replace";words[39]="reproduce";
		words[40]="revive";words[41]="remain";words[42]="remove";words[43]="represent";
		words[44]="interpret";words[45]="hopeful";words[46]="interaction";words[47]="dialog";
		words[48]="endless";words[49]="eastern";words[50]="transfer";words[51]="transform";
		words[52]="translate";words[53]="transplant";words[54]="depress";words[55]="despise";
		words[56]="depart";words[57]="detect";words[58]="descend";words[59]="detach";
		words[60]="western";words[61]="debate";words[62]="undergo";words[63]="undertake";
		words[64]="exhale";words[65]="expand";words[66]="explicit";words[67]="expose";
		words[68]="exaggerate";words[69]="exchange";words[70]="exhaust";words[71]="extinct";
		words[72]="exceed";words[73]="uneasy";words[74]="unfair";words[75]="unfortunate";
		words[76]="unlikely";words[77]="unusual";words[78]="unlock";words[79]="disagree";
		words[80]="disappear";words[81]="discourage";words[82]="disorder";words[83]="dismiss";
		words[84]="display";words[85]="dispose";words[86]="differ";words[87]="inevitable";
		words[88]="inexpensive";words[89]="illegal";words[90]="immoral";words[91]="dangerous";
		words[92]="typical";words[93]="costly";words[94]="friendly";words[95]="national";
		words[96]="different";words[97]="contrast";words[98]="withdraw";words[99]="withhold";
		words[100]="withstand";words[101]="separate";words[102]="secure";words[103]="select";
		words[104]="superior";words[105]="superb";words[106]="superficial";words[107]="fortunate";
		words[108]="surface";words[109]="uphold";words[110]="upright";words[111]="upset";
		words[112]="upside";words[113]="favorite";words[114]="suffer";words[115]="suggest";
		words[116]="support";words[117]="suppress";words[118]="anticipate";words[119]="antique";
		words[120]="advantage";words[121]="ancestor";words[122]="ancient";words[123]="advance";
		words[124]="company";words[125]="compile";words[126]="compose";words[127]="compromise";
		words[128]="comform";words[129]="confront";words[130]="correspond";words[131]="comfirm";
		words[132]="foolish";words[133]="sympathy";words[134]="symphony";words[135]="synchronize";
		words[136]="synthetic";words[137]="multimedia";words[138]="multiple";words[139]="absorb";
		words[140]="abnormal";words[141]="absolute";words[142]="selfish";words[143]="enable";
		words[144]="enforce";words[145]="enlarge";words[146]="enrich";words[147]="entitle";
		words[148]="enclose";words[149]="autograph";words[150]="automobile";words[151]="telepathy";
		words[152]="telescope";words[153]="perfect";words[154]="permanent";words[155]="familiar";
		words[156]="persist";words[157]="persuade";words[158]="perfume";words[159]="perspective";
		words[160]="adjust";words[161]="accompany";words[162]="account";words[163]="accumulate";
		words[164]="accuse";words[165]="satisfactory";words[166]="active";words[167]="approach";
		words[168]="abandon";words[169]="await";words[170]="arrogant";words[171]="aboard";
		words[172]="abroad";words[173]="alike";words[174]="arise";words[175]="arouse";
		words[176]="amaze";words[177]="ashamed";words[178]="geography";words[179]="geometry";
		words[180]="computer";words[181]="engineering";words[182]="university";words[183]="economic";
		words[184]="social";words[185]="union";words[186]="unique";words[187]="employee";
		words[188]="unity";words[189]="universe";words[190]="bicycle";words[191]="bilingual";
		words[192]="duplicate";words[193]="intellectual";words[194]="twist";words[195]="triangle";
		words[196]="artist";words[197]="triple";words[198]="truth";words[199]="leadership";
	
		for(int i=0; i<12; i++){
			print_word[i]="_";
		}
	}
	
	public void paint(Graphics screen){
		String StrLevel[] = {"Easy","Medium","Hard"};
		
		super.paint(screen);
		Graphics2D screen2D = (Graphics2D) screen;
		screen2D.setFont(normalFont);
		screen2D.drawImage(sample1, 260, 20, this);
		
		screen2D.setColor(Color.gray);
		screen2D.drawString("Made and copyright by: Heeseon Seo", 260, 510);
		screen2D.drawString("HansungUniversity ComputerEngineering", 260, 525);
		screen2D.drawString("tjgmltjs22@naver.com", 260, 540);
		screen2D.drawString("(ver "+version+")", 260, 555);
		
		screen2D.setFont(menuFont);
		screen2D.drawString("Wins "+win+", Looses "+loo,20,85);
		screen2D.drawString("WinningProsentige "+winPer+"%",20,105);
		switch(level){
		case 0: screen2D.drawString("Current skill level: "+StrLevel[0],20,50); break;
		case 1: screen2D.drawString("Current skill level: "+StrLevel[1],20,50); break;
		case 2: screen2D.drawString("Current skill level: "+StrLevel[2],20,50); break;
		}
		
		screen2D.setFont(warningFont);
		if(gameOn==0){
			screen2D.drawString("THIS IS HOME",580,100);
		}
		else{ 
			if(guessNum<=3)
				screen2D.setColor(Color.RED);
			else
				screen2D.setColor(Color.cyan);
			screen2D.drawString(guessNum+" guesses left",580,100);
		}
		
		if(guessNum>5){
			screen2D.drawImage(pin, 710, 170, this);
			screen2D.setColor(Color.orange);
			screen2D.fillOval(700-(40*(guessNum-5)), 200, 50, 50); //얼굴
			screen2D.setColor(Color.darkGray);
			screen2D.fillRect(700-(40*(guessNum-5))+20, 193, 2, 10); //머리
			screen2D.fillRect(700-(40*(guessNum-5))+25, 193, 2, 10);
			screen2D.fillRect(700-(40*(guessNum-5))+30, 193, 2, 10);
			screen2D.setColor(Color.BLUE);
			screen2D.fillRect(700-(40*(guessNum-5))+10, 210, 10, 3); //눈썹
			screen2D.fillRect(700-(40*(guessNum-5))+30, 210, 10, 3);
			screen2D.setColor(Color.BLACK);
			screen2D.fillOval(700-(40*(guessNum-5))+13, 217, 10, 10); //눈
			screen2D.fillOval(700-(40*(guessNum-5))+33, 217, 10, 10);
			screen2D.setColor(Color.RED);
			screen2D.fillOval(700-(40*(guessNum-5))+20, 228, 17, 17); //입
		}
		else if(guessNum>0){
			screen2D.drawImage(pin, 710, 170, this);
			screen2D.setColor(Color.orange);
			screen2D.fillOval(700-(40*1), 200, 50, 50); //얼굴
			if(guessNum>1){
				screen2D.setColor(Color.BLACK);
				screen2D.fillOval(700-(40*1)+13, 217, 10, 10); //왼쪽눈
			}
			if(guessNum>2){
				screen2D.setColor(Color.BLACK);
				screen2D.fillOval(700-(40*1)+33, 217, 10, 10); //오른쪽눈
			}
			if(guessNum>3){
				screen2D.setColor(Color.RED);
				screen2D.fillOval(700-(40*1)+20, 228, 17, 17); //입
			}
			if(guessNum>4){
				screen2D.setColor(Color.BLUE);
				screen2D.fillRect(700-(40*1)+10, 210, 10, 3); //눈썹
				screen2D.fillRect(700-(40*1)+30, 210, 10, 3);
			}
		}
		else
			screen2D.drawImage(bang, 660, 175, this);
		
		screen2D.setFont(wordCheckFont);
		screen2D.setColor(Color.BLACK);
		for(int i=0; i<12; i++){
			screen2D.drawString(print_word[i],20+(i*2*11),240);
		}
		
		if(bad==1){
			screen2D.setFont(warningFont);
			screen2D.setColor(Color.RED);
			screen2D.drawString("BAD!",370,240);
		}
		else if(bad==-1){
			screen2D.setFont(warningFont);
			screen2D.setColor(Color.blue);
			screen2D.drawString("GOOD!",360,240);
		}
	}
	
	public void wordSelect(){
		double sel_num;
		int selection;
        
        do{ //이미 선택된 단어가 다시 선택되는 경우는 배제
        	sel_num = Math.random() * 201;
        	selection = (int) Math.floor(sel_num);
        }while(word[selection] == 1);
        
        sel_word = words[selection].toUpperCase();
        word_length = sel_word.length();
        ch_sel_word = sel_word.toCharArray(); // character 배열로 변환
        word[selection]=1;
        
        for(int i=word_length; i<12; i++){
			print_word[i]="";
		}
        
        if(check_hint==1){
        	if(word_length>=6){
        		double hint_Dindex = Math.random() * word_length;
        		int hint_Iindex = (int) Math.floor(hint_Dindex);
        		print_word[hint_Iindex]=String.valueOf(ch_sel_word[hint_Iindex]);
        	}
        }
	}
	
	public void wordReset(){
		for(int i=0; i<12; i++){
			print_word[i]="_";
		}
		wordSelect();
	}
	
	public void adjustDisplay(){
		if(bad==-1){
			boolean is_end=true;
			for(int i=0; i<word_length; i++){
				if(print_word[i].compareTo(String.valueOf(ch_sel_word[i]))!=0){
					is_end=false;
					break;
				}
			}
			if(is_end){
				//단어추정성공
				check_hint=0;
				winSound=true;
				win++;
				winPer=(float)(win)/(win+loo)*100;
				for(int i=0; i<26; i++)
					MyJButton[i].setEnabled(false);
				begin.setEnabled(true);
				hint.setEnabled(true);
				back.setEnabled(false);
				if(level == 0) {
					medium.setEnabled(true);
					hard.setEnabled(true);
				} else if(level == 1) {
					easy.setEnabled(true);
					hard.setEnabled(true);
				} else if(level == 2) {
					easy.setEnabled(true);
					medium.setEnabled(true);
				}
			}
		}
		else{
			if(guessNum<=0){ //단어추정실패
				//looSound=true;
				check_hint=0;
				loo++;
				winPer=(float)(win)/(win+loo)*100;
				for(int i=0; i<26; i++)
					MyJButton[i].setEnabled(false);
				begin.setEnabled(true);
				hint.setEnabled(true);
				back.setEnabled(false);
				if(level == 0) {
					medium.setEnabled(true);
					hard.setEnabled(true);
				} else if(level == 1) {
					easy.setEnabled(true);
					hard.setEnabled(true);
				} else if(level == 2) {
					easy.setEnabled(true);
					medium.setEnabled(true);
				}
				//정답을 화면에 표시
				for(int i=0; i<word_length; i++){
					print_word[i]=String.valueOf(ch_sel_word[i]);
				}
			}
		}
		repaint();
	}
	
	public void spellCheck(char Spell){ //char spell은 대문자
		int check_key=0;
		for(int i=0; i<word_length; i++){
			if(ch_sel_word[i]==Spell){
				print_word[i]=""+Spell;
				check_key=1;
				bad=-1;
				repaint();
			}
		}
		if(check_key==0){
			guessNum--;
			bad=1;
			repaint();
		}
		adjustDisplay();
	}
	
	public void actionPerformed(ActionEvent e){
		String typed=e.getActionCommand();
		if(typed.equals("BACK")){
			check_hint=0;
			back.setEnabled(false);
			begin.setEnabled(true);
			hint.setEnabled(true);
			easy.setEnabled(false);
			medium.setEnabled(true);
			hard.setEnabled(true);
			for(int i=0; i<26; i++)
				MyJButton[i].setEnabled(false);
			
			level = 0;
			gameOn=0;
			bad=0;
			
			for(int i=0; i<12; i++){
				print_word[i]="_";
			}
			
			repaint();
		}
		if(typed.equals("BEGIN")) {
			back.setEnabled(true);
			begin.setEnabled(false);
			hint.setEnabled(false);
			easy.setEnabled(false);
			medium.setEnabled(false);
			hard.setEnabled(false);
			for(int i=0; i<26; i++)
				MyJButton[i].setEnabled(true);

			if(level == 0)
				guessNum = 10;
			else if(level == 1)
				guessNum = 8;
			else if(level == 2)
				guessNum = 6;
			
			gameOn=1;
			bad=0;
			wordReset();
			repaint();
		}
		if(typed.equals("HINT")) {
			check_hint=1;
			hint.setEnabled(false);
		}
		if(typed.equals("EASY")){
			easy.setEnabled(false);
			medium.setEnabled(true);
			hard.setEnabled(true);
			level = 0;
			repaint();
		}
		if(typed.equals("MEDIUM")) {
			medium.setEnabled(false);
			hard.setEnabled(true);
			easy.setEnabled(true);
			level = 1;
			repaint();
		}
		if(typed.equals("HARD")) {
			hard.setEnabled(false);
			medium.setEnabled(true);
			easy.setEnabled(true);
			level = 2;
			repaint();
		}
		for(int i=0; i<26; i++){
			if(typed.equals(String.valueOf(alphabet[i]))) {
				MyJButton[i].setEnabled(false);
				spellCheck(alphabet[i]); //대문자
			}
		}
		if(winSound){
			play(getCodeBase(),"win.au");
			winSound=false;
		}
//		if(looSound){
//			play(getCodeBase(),"loo.au");
//			looSound=false;
//		}
	}
}

