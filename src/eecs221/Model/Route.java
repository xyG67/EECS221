package eecs221.Model;

/**
 * 
 * @author gxy72
 * 用与计算路径
 * 变量：起始点(柜子)，连接点（柜子），距离
 *
 */

public class Route {
	
	private Cargo start;
	private Cargo end;
	private int length;
	
	public Route(Cargo start, Cargo end, WareHouse wh) {
		this.start = start;
		this.end = end;
		if(start.getAxio_X() == end.getAxio_X() && Math.abs(start.getAxio_Y() - end.getAxio_Y()) == 1) //一行
			this.length = wh.getAisle_Y();
		else if(start.getAxio_Y() == end.getAxio_Y() && Math.abs(start.getAxio_X() - start.getAxio_X()) == 1) { //一列
			this.length = wh.getAisle_X();
		}
	}

	public Cargo getStartNode() {
		return start;
	}

	public Cargo getEndNode() {
		return end;
	}

	public int getLength() {
		return length;
	}
	
	public Cargo getNeighbourIndex(Cargo node) {
		if(this.start == node) {
			return this.end;
		}else {
			return this.start;
		}
	}
}
