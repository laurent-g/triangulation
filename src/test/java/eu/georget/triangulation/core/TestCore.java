package eu.georget.triangulation.core;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class TestCore extends TestCase {

	static final Logger logger = Logger.getLogger(TestCore.class);

	CellSite cellSiteA;
	CellSite cellSiteB;
	CellSite cellSiteC;
	CellSite cellSiteD;
	CellSite cellSiteE;
	MobilePhone mobilePhone1;
	MobilePhone mobilePhone2;

	@Override
	protected void setUp() throws Exception {

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

	@Override
	protected void tearDown() throws Exception {
		/*
		 * Nothing to do
		 */
	}

	public void testMobilePhoneLocation() {

		Location location1 = mobilePhone1.getLocation();

		logger.debug("X: " + location1.getX());
		assertEquals(location1.getX(), 40);

		logger.debug("Y: " + location1.getY());
		assertEquals(location1.getY(), 80);
		
		Location location2 = mobilePhone2.getLocation();

		logger.info("X: " + location2.getX());
		assertEquals(location2.getX(), 42);

		logger.info("Y: " + location2.getY());
		assertEquals(location2.getY(), 84);
	}


}
