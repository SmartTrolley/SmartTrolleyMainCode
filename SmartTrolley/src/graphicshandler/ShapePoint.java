package graphicshandler;



/** 
*
* SmartTrolley
* PWS compliant Points for drawing graphics
*
* @author Matthew Wells
* @author Alasdair Munday
*
* @author [Checked By:] [Checker(s) fill here]
*
* @version [v1.0] [Date Created: 25/04/2014]
*/
public class ShapePoint implements Comparable<ShapePoint> {
	
	private int xCoordinate;
	private int yCoordinate;
	private int pointNumber;
	
	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param pointNumber
	 */
	public ShapePoint(int xCoordinate, int yCoordinate, int pointNumber) {
		this.pointNumber = pointNumber;
		this.setxCoordinate(xCoordinate);
		this.setyCoordinate(yCoordinate);
	}
	
	
	/**ShapePoints should be organised by pointNumber when used to draw a shape
	 * 
	 * Required by Comparable Interface:
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * <p> Date Modified: 25 Apr 2014
	 */
	@Override
	public int compareTo(ShapePoint pointToCompare)	{
		return Double.compare(pointToCompare.pointNumber, pointNumber);
	}

	/**
	*Return the x coordinate of the point
	*<p>Calling Class returns X coordinate
	*@return xCoordinate
	*<p> Date Modified: 25 Apr 2014
	*/
	public int getxCoordinate() {
		return xCoordinate;
	}

	/**
	*Set the X coordinate of the point
	*<p>C
	*@param xCoordinate
	*<p> Date Modified: 25 Apr 2014
	*/
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	
	/**	
	*Return the y coordinate of the point
	*<p>Calling Class returns Y coordinate
	*@return yCoordinate
	*<p> Date Modified: 25 April 2014
	*/
	public int getyCoordinate() {
		return yCoordinate;
	}

	/**
	*Set the Y coordinate of the point
	*<p>C
	*@param yCoordinate
	*<p> Date Modified: 25 Apr 2014
	
	*/
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
}
