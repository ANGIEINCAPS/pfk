package game;

import java.util.concurrent.TimeUnit;

public class TakePinsGame {

	public static void main(String[] args) {
		/* Board set up, skapar spelare. */
		Board b = new Board();
		b.setUp(10);
		Player p1 = new HumanPlayer("David Bowman");
		Player p2 = new ComputerPlayer("HAL 9000");
		
		/* Skriver ut hur många pins som finns*/
		System.out.print(b.toString());

		while(true) {
			if (b.getNoPins() < 2) {
				System.out.println(p2.getUserId() + " won!");
				break;
			} else {
				p1.takePins(b);
				System.out.println(b.toString());
			}
			if (b.getNoPins() < 2) {
				System.out.println(p1.getUserId() + " won!");
				break;
			} else {
				p2.takePins(b);
				System.out.println(b.toString());
			}
		}

	}

}
