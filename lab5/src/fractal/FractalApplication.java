package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Mountain(new Point(100, 550), new Point(500, 500), new Point(250, 150), 10.0);
		fractals[1] = new Koch(300);
		
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
