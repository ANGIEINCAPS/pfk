package game;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String userId) {
		super(userId);

	}

	@Override
	public int takePins(Board b) {
		int n;
		double r = Math.random();
		if (r < 0.5) {
			n = 1;
		} else {
			n = 2;
		}
		System.out.println("It's " + this.userId + "'s turn. " + "\n" + userId + " took " + n + " pins.");
		return b.takePins(n);
	}

}
