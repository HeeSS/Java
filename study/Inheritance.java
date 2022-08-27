class People{
	String name = "Hong, Gil-Dong";
	int age = 20;
	void showNameAge(){
		System.out.println("Name :" + name);
		System.out.println("Age :" + age);
	}
}
class Student extends People{
	int studentNumber = 1234;
	void showStudentNum(){
		System.out.println("Student Number :" + studentNumber);
	}
}
public class Inheritance{
	public static void main(String[] args){
		Student obj = new Student();
		obj.showNameAge();
		obj.showStudentNum();
	}
}