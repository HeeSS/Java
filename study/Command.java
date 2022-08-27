class Command{
	public static void main(String[] args){
		int x;
		int sum=0;
		for(x=0; x<args.length; x++){
			System.out.println(args[x]);
			sum += Integer.parseInt(args[x]);
		}
		System.out.println(sum);
	}
}