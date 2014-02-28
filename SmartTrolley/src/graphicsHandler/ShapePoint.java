package graphicsHandler;

public class ShapePoint implements Comparable<ShapePoint> {
	
	private int xCoordinate;
	private int yCoordinate;
	private int pointNumber;
	
	public ShapePoint(int xCoordinate, int yCoordinate, int pointNumber) {
		this.pointNumber = pointNumber;
		this.setxCoordinate(xCoordinate);
		this.setyCoordinate(yCoordinate);
	}
	
	
	//ShapePoints should be organised by pointNumber when used to draw a shape
	@Override
	public int compareTo(ShapePoint pointToCompare)	{
		return Double.compare(pointToCompare.pointNumber, pointNumber);
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
}
