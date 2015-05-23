package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArrayListModel.class, TestDatabase.class, TestMedia.class,
		TestMediaAlgorithm.class, TestUtils.class })
public class AllTests {

}
