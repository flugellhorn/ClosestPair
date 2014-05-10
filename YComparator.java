
import java.awt.Point;
import java.util.Comparator;

public class YComparator implements Comparator<Point>{
	
	public int compare(Point a, Point b) {
		if (a.y == b.y)
			return 0;
		if  (a.y > b.y)
			return 1;
		else
			return -1;
	}
}
