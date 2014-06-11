package toolBox;

/**
 * SmartTrolley
 *
 * This class is a toolbox of utility methods for the SmartTrolley application
 *
 * @author Prashant Chakravarty
 * @author Alick Jacklin
 *
 * @author Checked By: Thomas Lea
 *
 * @version V1.0 [Date Created: 11 Mar 2014]
 * @version V2.0 [Date Created: 25 May 2014] - Combined the delay and delay into one toolbox class in a toolbox package 
 */
public class SmartTrolleyToolBox {

	/**
	 *Prints, then flushes buffer
	 *@param text String to be printed
	 *<p> Date Modified: 11 Mar 2014
	 */
	public static void print(Object text) {
		System.out.println(text);
		System.out.flush();
	}
	
	/**
	*Inserts a delay in milliseconds into the current code (by sleeping the thread) when it is called
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

/**************End of SmartTrolleyToolBox.java**************/
