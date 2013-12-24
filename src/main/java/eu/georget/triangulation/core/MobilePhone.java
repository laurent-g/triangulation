package eu.georget.triangulation.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import eu.georget.triangulation.util.ITriangulationAlgorithm;
import eu.georget.triangulation.util.SimpleTriangulationAlgorithm;

/**
 * MobilePhone is a class which allow an application to represent a mobile phone
 * A mobile phone has neighbor cell sites and can compute its location
 * 
 * @author laurent
 * 
 * @see ILocatable
 */
public class MobilePhone implements ILocatable {

	static final Logger logger = Logger.getLogger(MobilePhone.class);

	/**
	 * Triangulation algorithm
	 */
	ITriangulationAlgorithm triangulationAlgorithm;

	/**
	 * List of neighbor cell sites with signal strengh
	 */
	Map<CellSite, Integer> neighborCellSites;

	/**
	 * 
	 */
	public MobilePhone() {
		super();
		neighborCellSites = new HashMap<CellSite, Integer>();

		logger.debug("New MobilePhone");
	}

	/**
	 * Return location of the mobile phone, in this case the location is
	 * computed using a triangulation algorithm
	 * 
	 * @see Location
	 */
	@Override
	public Location getLocation() {
		return getTriangulationAlgorithm().computeLocation(neighborCellSites);
	}

	/**
	 * Registers a new cell site
	 * 
	 * @param cellSite
	 *            Cell site
	 * @param signalStrengh
	 *            Signal strength
	 * 
	 * @see CellSite
	 */
	public void registerCellSite(CellSite cellSite, int signalStrengh) {

		neighborCellSites.put(cellSite, signalStrengh);

		logger.debug(String.format(
				"Registering new cell site : %s with strength=%d",
				cellSite.getId(), signalStrengh));
	}

	/**
	 * Return the triangulation algorithm implementation used by a mobile phone
	 * 
	 * @return Triangulation algorithm implementation
	 */
	private ITriangulationAlgorithm getTriangulationAlgorithm() {
		if (triangulationAlgorithm == null) {
			triangulationAlgorithm = new SimpleTriangulationAlgorithm();
		}
		return triangulationAlgorithm;
	}
}
