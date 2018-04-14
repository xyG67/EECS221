package eecs221.Model;

import java.util.Comparator;

public class Cargo implements Comparable<Cargo>, Comparator<Cargo>{
	private int number;
	private int axio_X;   //+1
	private int axio_Y;   //+1
	
	private int axio_X_wh;
	private int axio_Y_wh;

	//If input is String 
	public Cargo(String number, String axio_X, String axio_Y){
		this.number = Integer.parseInt(number);
		this.axio_X = (int)(double)Double.parseDouble(axio_X) + 1;
		this.axio_Y = Integer.parseInt(axio_Y) + 1;
	}
	//If input is Integer
	public Cargo(int number, int axio_X, int axio_Y){
		this.number = number;
		this.axio_X = axio_X + 1;
		this.axio_Y = axio_Y + 1;
	}
	//Generate node on realize position
	
	public void generateRealNode(Shelf s, WareHouse wh) {
		if(this.axio_X % 2 == 1) {  //left
			this.axio_X_wh = this.axio_X * (this.axio_X/2) * s.getWeight() ;
			this.axio_Y_wh = this.axio_Y * this.axio_Y * s.getHeight();
		}else {
			this.axio_X_wh = this.axio_X * (this.axio_X/2) * s.getWeight() + s.getWeight();
			this.axio_Y_wh = this.axio_Y * this.axio_Y * s.getHeight();
		}
	}
	
	public void shortestPath() {
		
	}
	
	public int getNumber() {
		return number;
	}

	public int getAxio_X() {
		return axio_X;
	}

	public int getAxio_Y() {
		return axio_Y;
	}

	@Override
	public int compare(Cargo cargo1, Cargo cargo2) {
		return cargo1.number - cargo2.number;
	}

	@Override
	public int compareTo(Cargo cargo) {
		return number-cargo.number;
	}
}
