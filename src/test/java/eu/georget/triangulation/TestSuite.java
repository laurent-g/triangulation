package eu.georget.triangulation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eu.georget.triangulation.core.TestCore;
import eu.georget.triangulation.io.TestIO;

@RunWith(Suite.class)
@SuiteClasses({TestCore.class, TestIO.class})
public class TestSuite {

}
