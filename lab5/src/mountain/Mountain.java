package mountain;

import java.util.HashMap;
import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private HashMap<Side, Point> sideMap;

	public Mountain(Point a, Point b, Point c, double dev) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		this.sideMap = new HashMap<>();

	}

	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, this.order, this.a, this.b, this.c, this.dev);

	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Point ab = newPoint(a, b, dev);
			Point bc = newPoint(b, c, dev);
			Point ac = newPoint(a, c, dev);

			Point[][] lines = new Point[][] { { a, ab, ac }, { ab, b, bc }, { ac, bc, c }, { bc, ac, ab } };

			for (Point[] p : lines) {
				fractalTriangle(turtle, order - 1, p[0], p[1], p[2], dev);
			}
		}
	}

	private Point newPoint(Point p, Point q, double dev) {
		Side s = new Side(p, q);
		
		if (sideMap.containsKey(s)) {
			Point r = sideMap.get(s);
			sideMap.remove(s);
			
			return r;
		}
		int x = p.getX() + (q.getX() - p.getX()) / 2;
		int y = (int) ((p.getY() + (q.getY() - p.getY()) / 2) + (RandomUtilities.randFunc(dev) / 2));
		
		Point r = new Point(x, y);
		sideMap.put(s, r);

		return r;
	}

}
