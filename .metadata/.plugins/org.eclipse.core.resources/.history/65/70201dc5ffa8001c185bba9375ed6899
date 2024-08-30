package set;

public class Person {
	private String name;
	private int id;
	
	public Person (String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Person) {
			return  (id == ((Person) other).id);
		} else { 
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		return name + " " + id;
	}

}
