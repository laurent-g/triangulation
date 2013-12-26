package eu.georget.triangulation;

import org.apache.log4j.Logger;

import eu.georget.triangulation.core.CellSite;
import eu.georget.triangulation.core.Location;
import eu.georget.triangulation.core.MobilePhone;

/**
 * First Main App
 * 
 */
public class AppCore {

	static final Logger logger = Logger.getLogger(AppCore.class);

	public static void main(String[] args) {

		logger.info("*** CELL SITE");
		CellSite cellSiteA = new CellSite("A", new Location(0, 0));
		CellSite cellSiteB = new CellSite("B", new Location(0, 100));
		CellSite cellSiteC = new CellSite("C", new Location(100, 100));
		CellSite cellSiteD = new CellSite("D", new Location(100, 0));

		logger.info("*** MOBILE 1");

		MobilePhone mobilePhone1 = new MobilePhone();
		mobilePhone1.registerCellSite(cellSiteA, 1);
		mobilePhone1.registerCellSite(cellSiteB, 2);
		mobilePhone1.registerCellSite(cellSiteC, 2);
		Location location1 = mobilePhone1.getLocation();

		logger.debug("X: " + location1.getX());
		logger.debug("Y: " + location1.getY());

		logger.info("*** MOBILE 2");

		MobilePhone mobilePhone2 = new MobilePhone();
		mobilePhone2.registerCellSite(cellSiteB, 11);
		mobilePhone2.registerCellSite(cellSiteC, 5);
		mobilePhone2.registerCellSite(cellSiteD, 3);
		Location location2 = mobilePhone2.getLocation();

		logger.info("X: " + location2.getX());
		logger.info("Y: " + location2.getY());
	}
}
