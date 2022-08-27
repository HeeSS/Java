class String_4{
	public static void main(String args[]){
		String str = "abcde";
		String key = new String("abcd");
		int result;
		result = key.compareTo(str);
		System.out.println("result = " + result);
	}
}