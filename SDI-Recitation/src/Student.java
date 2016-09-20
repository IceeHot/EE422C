
public class Student extends Person implements HelloSayer, GoodbyeSayer {
	public Student() {
		super();
	}
	
	@Override
	public void sayGoodbye() {
		System.out.println(getMessagePrefix() + "Good bye!");
	}
	
	@Override
	public void sayHello() {
		System.out.println(getMessagePrefix() + "Hello!");
	}
	
	@Override
	public String getName() {
		return "My name";
	}
}
