class String_5{
	public static void main(String args[]){
		String s1 = "java";
		String s2 = "java";
		String s3 = "JAVA";
		String s4 = "good";
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
		System.out.println(s1.equals(s4));
		System.out.println(s1.equalsIgnoreCase(s3));
	}
}