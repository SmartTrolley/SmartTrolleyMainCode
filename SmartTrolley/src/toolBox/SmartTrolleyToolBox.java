package toolBox;

/**
 * SmartTrolley
 *
 * Our own print statement 
 *
 * @author Prashant Chakravarty
 * @author Alick Jacklin
 *
 * @author Checked By: Thomas Lea
 *
 * @version version of this file [Date Created: 11 Mar 2014]
 */

public class SmartTrolleyToolBox {

	/**
	 *prints, then flushes buffer
	 *@param text String to be printed
	 *<p> Date Modified: 11 Mar 2014
	 * @return 
	 */
	public static void print(Object text) {
		System.out.println(text);
		System.out.flush();
	}
	
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

/**************End of SmartTrolleyToolBox.java**************/
