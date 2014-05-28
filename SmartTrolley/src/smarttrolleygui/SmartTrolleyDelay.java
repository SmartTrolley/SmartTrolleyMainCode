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


package smarttrolleygui;

public class SmartTrolleyDelay {
		
	/**
	*Inserts a delay in milliseconds into the current code when it is called
	*@param ms
	*<p> Date Modified: 13 May 2014
	*/
	public static void delay(int ms){
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}

}

/**************End of SmartTrolleyPrint.java**************/