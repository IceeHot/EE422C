package assignment3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BlankStringTest.class, DiffLengthTest.class, MatchingLettersTest.class, NotInDictionaryTest.class,
		SameWordTest.class })
public class AllTests {

}
