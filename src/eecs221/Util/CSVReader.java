package eecs221.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import eecs221.Model.Cargo;

public class CSVReader {

	public String fileLocation;
//	private int[][] myData;
//	private Set[][] myMatrix;

	public CSVReader(String xfileLocation) {
		this.fileLocation = xfileLocation;
	}

	public ArrayList<Cargo> setUpArray(int row, int col) {
		// For HW1 row = 18*2 and column = 10*2
//		this.myData = new int[row * 2 + 1][col * 2 + 1];
//		this.myMatrix = new Set[row + 1][col + 1];
		ArrayList<Cargo> cargos = new ArrayList<>();

		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		String InputLine = "";

		try {
			br = new BufferedReader(new FileReader(fileLocation));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Iterator<String> it = dataList.iterator();
		while (it.hasNext()) {
			InputLine = it.next();
			String[] InArray = InputLine.split(", ");

			Cargo cargo = new Cargo(InArray[0], InArray[1], InArray[2]);
			cargos.add(cargo);

//			double curr_x = Double.parseDouble(InArray[1]);
			
			//myData[((int) curr_x) * 2][Integer.parseInt(InArray[2]) * 2] = Integer.parseInt(InArray[0]);
			//System.out.println(Integer.parseInt(InArray[0]));
		
//			if(myMatrix[((int) curr_x)][Integer.parseInt(InArray[2])] == null) {
//				Set curr = new HashSet();
//				curr.add(Integer.parseInt(InArray[0]));
//				myMatrix[((int) curr_x)][Integer.parseInt(InArray[2])] = curr;
//			}else {
//				(myMatrix[((int) curr_x)][Integer.parseInt(InArray[2])]).add(Integer.parseInt(InArray[0]));
//			}
		}
		return cargos;
	}

//	public int[][] getMyData() {
//		for(int i = 0 ; i < myMatrix.length ; i++) {
//			Set[] curr = myMatrix[i];
//			for(int j = 0 ; j < curr.length ; j++) {
//				//System.out.println("position: "+ i + " "+ j+ "---> " +myMatrix[i][j]);
//			}
//		}
//		return myData;
//	}
}
