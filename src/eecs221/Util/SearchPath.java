package eecs221.Util;

import java.util.ArrayList;
import java.util.Collections;

import eecs221.Model.Cargo;
import eecs221.Model.Shelf;
import eecs221.Model.WareHouse;

public class SearchPath {
	
	private Shelf s;
	private WareHouse wh;
	
	public SearchPath(Shelf s, WareHouse wh){
		this.s = s;
		this.wh = wh;
		
	}
	
	public int[] findSingleItem(ArrayList<Cargo> cargos, int number){
		int[] res = new int[2];
		
		int index = Collections.binarySearch(cargos, new Cargo(number, 0, 0));
		if(index >= 0) {
			System.out.println("The axio of given number is : "+ cargos.get(index).getAxio_X() + ", "+ cargos.get(index).getAxio_Y());
			res[0] = cargos.get(index).getAxio_X();
			res[1] = cargos.get(index).getAxio_Y();
		} else
			System.out.println("This item does not exist!");
		return res;
	}
	
	public int[] findMiddlePoint(int[] targetpoint, int[] startpoint) {		
		int[] res = new int[2];
		if(targetpoint[0] == 0 && targetpoint[1] == 0) return res;
		
		//int s_height = s.getHeight();
		int s_weight = s.getWeight();
		int wh_X = wh.getAisle_X();
		//int wh_Y = wh.getAisle_Y();
		
		int curr_X = (targetpoint[0] - startpoint[0] - 1 )/2 + 1;
		
		if(targetpoint[0] % 2 != 0) {
			res[0] = (curr_X) * (s_weight + wh_X);
			System.out.println("The cargo is at the right site of shelf No."+(curr_X));
		} else {
			res[0] = curr_X * (s_weight + wh_X) + wh_X;
			System.out.println("The cargo is at the left site of shelf No."+(curr_X + 1));
		}
		System.out.println("Start point: (0, 0)");
		
//		int curr_Y = (targetpoint[1] - startpoint[1])/2;
//		if(targetpoint[1]%2 == 0) {
//			res[1] = (curr_Y + 1)*(s_height + wh_Y);
//		} else {
//			res[1] = curr_Y*(s_height + wh_Y) + wh_Y;
//		}
		System.out.println("turn point is: "+ res[0]+", "+ res[1]);
		return res;
	}
	
}
