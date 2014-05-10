
/*
 * Name: Andrew Szlembarski
 * Program: Closest Pair
 * Worked with: Adrian Baran, Alex Rymarz, Mike Hoye
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;

public class ClosestPair {
	
	private static ArrayList<String> answer  = new ArrayList<String>();
	private static ArrayList<Point> pairList = new ArrayList<Point>();
	private static double response = 0;

    private static double recPair(Point[] p, int i, int j){	
    	if (j - i < 3){
    		Arrays.sort(p, i, j, new YComparator());
    		double delta = p[i].distanceSq(p[i + 1]);
    		if(pairList.size() == 0){
    			pairList.add(p[i]);
    			pairList.add(p[i + 1]);
    		}
			if(pairList.size() == 2){
				if (p[i].distanceSq(p[i + 1]) < pairList.get(0).distanceSq(pairList.get(1))){
					pairList.set(0, p[i]);
					pairList.set(1, p[i + 1]);
				}
			}
			if (j - i == 1){
				response = delta;
    			return delta;
    		}
    		if (p[i+1].distanceSq(p[i+2]) < pairList.get(0).distanceSq(pairList.get(1))){
    			pairList.set(0, p[i + 1]);
    			pairList.set(1, p[i + 2]);
    		}
    		if (p[i].distanceSq(p[i+2]) < pairList.get(0).distanceSq(pairList.get(1))){
    			pairList.set(0, p[i]);
    			pairList.set(1, p[i + 2]);
			}
    		response = delta;
    		return delta;
    	}
    	int k = (i + j)/2;
    	int l = p[k].x;
    	recPair(p, i, k);
    	recPair(p, k, j);
    	double delta = Math.sqrt(pairList.get(0).distanceSq(pairList.get(1)));
    	Arrays.sort(p, i, j, new YComparator());
    	ArrayList<Point> v = new ArrayList<Point>(); 
    	int t = 0;
    	for (int q = i; q < j; q++){
    		if (p[q].x > (l - delta) && p[q].x < (l + delta)){
    			v.add(p[q]);
    			t++;
    		}
    	}
    	for (int x = 0; x < t; x++){
    		for (int y = x + 1; y < Math.min(t, x + 7); y++){
    			double del = v.get(x).distanceSq(v.get(y));
    			if (del < pairList.get(0).distanceSq(pairList.get(1))){
    				pairList.set(0, v.get(x));
    				pairList.set(1, v.get(y));
    				delta = del;
    			}
    		}
    	}
    	response = delta;
    	return delta;
    }
    
	private static void closestPair(Point[] p){
		int n = p.length;
		Arrays.sort(p, new XComparator());
		recPair(p, 0, n-1);	
    }
	
	private static ArrayList<String> solve(ArrayList<String> data){
		answer.clear();
		pairList.clear();
    	String[] pair = new String[2];
    	Point[] points = new Point[data.size()];
    	for (int i = 0; i < data.size(); i++){
    		pair = data.get(i).split(",");
    		int x = Integer.parseInt(pair[0]);
    		int y = Integer.parseInt(pair[1]);
    		Point temp = new Point(x,y);
    		points[i] = temp;
    	}
    	closestPair(points);
    	if(pairList.size() != 0){
    		answer.add(pairList.get(0).x +","+ pairList.get(0).y);
    		answer.add(pairList.get(1).x +","+ pairList.get(1).y);
    	}
    	return answer;
	}
	
	public static void main(String[] args){
		ArrayList<String> x = new ArrayList<String>();
		x.add("1,2");
		x.add("4,6");
		x.add("7,1");
		x.add("3,8");
		x.add("123,2");
		solve(x);
		System.out.println("Closest Pair: " + pairList.get(0).x + "," + pairList.get(0).y + "  " + pairList.get(1).x + "," + pairList.get(1).y);
	}
}
