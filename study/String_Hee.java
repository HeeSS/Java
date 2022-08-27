class String_Hee{
	public static void main(String a[]){
		String x = "The World Wide Web (abbreviated as the Web or WWW) is a system of Internet servers that supports hypertext to access several Internet protocols on a single interface Almost every protocol type available on the Internet is accessible on the Web. This includes e-mail, FTP, Telnet, and Usenet News. In addition to these, the World Wide Web has its own protocol: HyperText Transfer Protocol, or HTTP. These protocols will be explained later in this handout. The World Wide Web provides a single interface for accessing all these protocols. This creates a convenient and user-friendly environment. It is no longer necessary to be conversant in these protocols within separate, command-level environments. The Web gathers together these protocols into a single system. Because of this feature, and because of the Web's ability to work with multimedia and advanced programming languages, the World Wide Web is the fastest-growing component of the Internet. The operation of the Web relies primarily on hypertext as its means of information retrieval. HyperText is a document containing words that connect to other documents. These words are called links and are selectable by the user. A single hypertext document can contain links to many documents. In the context of the Web, words or graphics may serve as links to other documents, images, video, and sound. Links may or may not follow a logical path, as each connection is programmed by the creator of the source document. Overall, the WWW contains a complex virtual web of connections among a vast number of documents, graphics, videos, and sounds. Producing hypertext for the Web is accomplished by creating documents with a language called HyperText Markup Language, or HTML. With HTML, tags are placed within the text to accomplish document formatting, visual features such as font size, italics and bold, and the creation of hypertext links. Graphics may also be incorporated into an HTML document. HTML is an evolving language, with new tags being added as each upgrade of the language is developed and released. The World Wide Web Consortium, led by Web founder Tim Berners-Lee, coordinates the efforts of standardizing HTML. The World Wide Web consists of files, called pages or home pages, containing links to documents and resources throughout the Internet. The Web provides a vast array of experiences including multimedia presentations, real-time collaboration, interactive pages, radio and television broadcasts, and the automatic \"push\" of information to a client computer. Programming languages such as Java, JavaScript and Visual Basic are extending the capabilities of the Web. An increasing amount of information on the Web is served dynamically from content stored in databases. The Web is therefore not a fixed entity, but one that is in a constant state of flux.";



		String s[] = x.split(" +");
		int count, max, index;
		int result[][] = new int[26][3];
		char ch, ch_up;
		int quiz=0;
		int aNum=0, nNum=0;

		index=0;
		for(ch='a'; ch<='z'; ch++){
			count=0;
			for(int i=0; i<x.length(); i++){
				if(x.charAt(i)==ch)	count++;
			}
			result[index++][0] = count;
			//System.out.println(ch+" 개수 = "+count);
		}
	
		//Quiz 문제
		for(int i=0; i<s.length; i++){
			aNum=0; nNum=0;
			for(int j=0; j<s[i].length(); j++){
				if(s[i].charAt(j)=='a' || s[i].charAt(j)=='A') aNum=1;
			}
			for(int j=0; j<s[i].length(); j++){
				if(s[i].charAt(j)=='n' || s[i].charAt(j)=='N') nNum=1;
			}
			if((aNum==1) && (nNum==1)){
				System.out.println(s[i]);
				quiz++;
			}
		}
		System.out.println("##Quiz 답: "+quiz);
		
		index=0;
		for(ch='A'; ch<='Z'; ch++){
			count=0;
			for(int i=0; i<x.length(); i++){
				if(x.charAt(i)==ch)	count++;
			}
			result[index++][1] = count;
			//System.out.println(ch+" 개수 = "+count);
		}

		ch = 'a';
		ch_up = 'A';
		for(int i=0; i<result.length; i++){
			result[i][2] = result[i][0] + result[i][1];
			System.out.println(ch + " = " + result[i][0] + "개, " + ch_up + " = " + result[i][1] + "개, sum = " + result[i][2] + "개");
			ch++; ch_up++;	
		}

		System.out.println("단어개수 = "+s.length);

		//가장 긴 단어와 단어의 알파벳 개수 출력
		index=0;
		max = s[index].length();
		for(int i=0; i<s.length; i++){
			if(max<s[i].length()){
				max = s[i].length();
				index = i;
			}
		}
		System.out.println("가장 긴 단어 = " + s[index] + ", 알파벳개수(max) = " + max);
	}
}