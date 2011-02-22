package maps;

import java.util.Random;

public class MapTest {
	
	private MapWriter mw;
	private MapReader mr;
	
	public MapTest() {
		mw = new MapWriter("mymap.map");
		createMap();
		mw.close();
		
		int window = 3;
		mr = new MapReader("mymap.map",window);
		
		byte[] next;		
		do {
			System.out.println(mr);
			next = mr.next();
		} while(next != null);
		
		mr.close();
	}
	
	private void createMap() {
		Random r = new Random();
		for(int i=0; i<20; i++) {
			mw.add((byte)0);
			mw.add((byte)r.nextInt(10));
			mw.add((byte)1);
		}
	}

}
