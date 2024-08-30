package game;

import java.util.Scanner;

public class HumanPlayer extends Player {
	Scanner s = new java.util.Scanner(System.in);

	public HumanPlayer(String userId) {
		super(userId);

	}

	@Override
	public int takePins(Board b) {
		int n = 0;

		while (n <= 0 || n > b.getNoPins()) {
			System.out.println("It's " + userId + "'s turn. " + "Select the amount of pins to pick: ");
			n = s.nextInt();
		}
		System.out.println(userId + " took " + n + " pins.");
		return b.takePins(n);

	}

}
