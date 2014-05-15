/**
* SmartTrolley
*
* A DESCRIPTION OF THE FILE
*
* @author Alick Jacklin
* @author Prashant Chakravarty
*
* @author Checked By: Checker(s) fill here
*
* @version V1.0 [Date Created: 13 May 2014]
*/


package tests;

public class SmartTrolleyDelay {
		
	/**
	*Inserts a delay into the current code when it is called
	*@param delay
	*<p> Date Modified: 13 May 2014
	*/
	public static void delay(int delay){
		
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}

}

/**************End of SmartTrolleyDelay.java**************/