
/**
 * SmartTrolley
 *
 * Class to run all tests
 *
 * @author Alick Jacklin
 * @author Prashant Chakravarty
 *
 * @author Checked By: Checker(s) fill here
 *
 * @version V1.0 [Date Created: 01 Mar 2014]
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({server.ServerTestSmartTrolleyController.class, client.ClientTestSmartTrolleyController.class
})

public class AllServerClientTests {

}

/**************End of AllServerClientTests.java**************/