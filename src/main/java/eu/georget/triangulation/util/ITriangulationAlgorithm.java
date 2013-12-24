package eu.georget.triangulation.util;

import java.util.Map;

import eu.georget.triangulation.core.CellSite;
import eu.georget.triangulation.core.Location;

/**
 * ITriangulationAlgorithm provide a common behavior for different algorithm implementations
 * that compute location based on known cell site locations and signal strength
 * 
 * @author laurent
 * 
 */
public interface ITriangulationAlgorithm {

	/**
	 * Return a location computed from a map of CellSite/Strength
	 * 
	 * @param cellSites
	 *            Map with cell site as key and signal strength as value
	 * @return Location
	 */
	public Location computeLocation(Map<CellSite, Integer> cellSites);

}
