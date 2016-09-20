
public class Main {

	public static void main(String[] args) {
		Student s = new Student();
		
		System.out.println("s instanceof Student?: " + (s instanceof Student));
		System.out.println("s instanceof Person?: " + (s instanceof Person));
		System.out.println("s instanceof HelloSayer?: " + (s instanceof HelloSayer));
		System.out.println("s instanceof GoodbyeSayer?: " + (s instanceof GoodbyeSayer));
	}

}
