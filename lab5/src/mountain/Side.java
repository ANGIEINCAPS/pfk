package mountain;

public class Side {
	private Point p;
	private Point q;

	public Side(Point p, Point q) {
		this.p = p;
		this.q = q;
	}

	@Override
	public boolean equals(Object obj) {
		Side s = (Side) obj;
		return this.p.equals(s.p) && this.q.equals(s.q) ||  this.p.equals(s.q) && this.q.equals(s.p);
	}

	@Override
	public int hashCode() {
		return p.hashCode() + q.hashCode();
	}

}
