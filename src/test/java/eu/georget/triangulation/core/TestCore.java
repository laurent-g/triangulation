package eu.georget.triangulation.core;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCore {

	static final Logger logger = Logger.getLogger(TestCore.class);

	public TestCore() {
		super();
	}

	CellSite cellSiteA;
	CellSite cellSiteB;
	CellSite cellSiteC;
	CellSite cellSiteD;
	CellSite cellSiteE;
	MobilePhone mobilePhone1;
	MobilePhone mobilePhone2;

	@Before
	public void before() {

		/*
		 * Cell sites
		 */
		cellSiteA = new CellSite("A", new Location(0, 0));
		cellSiteB = new CellSite("B", new Location(0, 100));
		cellSiteC = new CellSite("C", new Location(100, 100));
		cellSiteD = new CellSite("D", new Location(100, 0));

		/*
		 * Mobile phones
		 */
		mobilePhone1 = new MobilePhone();
		mobilePhone1.registerCellSite(cellSiteA, 1);
		mobilePhone1.registerCellSite(cellSiteB, 2);
		mobilePhone1.registerCellSite(cellSiteC, 2);

		mobilePhone2 = new MobilePhone();
		mobilePhone2.registerCellSite(cellSiteB, 11);
		mobilePhone2.registerCellSite(cellSiteC, 5);
		mobilePhone2.registerCellSite(cellSiteD, 3);
	}

	@After
	public void after() {
		/*
		 * Nothing to do
		 */
	}

	@Test
	public void testMobilePhoneLocation() {

		Location location1 = mobilePhone1.getLocation();

		logger.debug("X: " + location1.getX());
		Assert.assertEquals(location1.getX(), 40);

		logger.debug("Y: " + location1.getY());
		Assert.assertEquals(location1.getY(), 80);

		Location location2 = mobilePhone2.getLocation();

		logger.info("X: " + location2.getX());
		Assert.assertEquals(location2.getX(), 42);

		logger.info("Y: " + location2.getY());
		Assert.assertEquals(location2.getY(), 84);
	}

}
