package game;

public class Board {
	private int noPins;

	public Board() {
		this.noPins = 0;
	}

	public void setUp(int n) {
		this.noPins = n;
	}

	public int takePins(int n) {
		return this.noPins -= n;
	}

	public int getNoPins() {
		return this.noPins;
	}

	public String toString() {
		return "Board has " + this.getNoPins() + " pins left.";
	}

}
