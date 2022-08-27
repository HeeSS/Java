class String_8{
	public static void main(String a[]){
		String x = "Hansung           University is          the best school in the world. It is in Seoul, Korea.";
		String s[] = x.split(" +");
		int count, max, index=0;
		char ch;
		for(ch='a'; ch<='z'; ch++){
			count=0;
			for(int i=0; i<x.length(); i++){
				if(x.charAt(i)==ch)	count++;
			}
			System.out.println(ch+" 개수 = "+count);
		}
		System.out.println("단어개수 = "+s.length);

		max = s[index].length();
		for(int i=0; i<s.length; i++){
			if(max<s[i].length()){
				max = s[i].length();
				index = i;
			}
		}
		System.out.println(s[index] + ", max = " + max);
	}
}