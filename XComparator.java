
import java.awt.Point;
import java.util.Comparator;

public class XComparator implements Comparator<Point> {
	
	public int compare(Point a, Point b) {
		if (a.x == b.x)
			return 0;
		if  (a.x > b.x)
			return 1;
		else
			return -1;
    }

}
