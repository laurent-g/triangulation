package eu.georget.triangulation.util;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import eu.georget.triangulation.core.CellSite;
import eu.georget.triangulation.core.Location;

/**
 * Simple implementation of ITriangulationAlgorithm : x = ( g1 * x1 + g2 * x2 +
 * .. + gi * xi ) / ( g1 + g2 + ... + gi) y = ( g1 * y1 + g2 * y2 + .. + gi * yi
 * ) / ( g1 + g2 + ... + gi)
 * 
 * @author laurent
 * 
 */
public class SimpleTriangulationAlgorithm implements ITriangulationAlgorithm {

	static final Logger logger = Logger
			.getLogger(SimpleTriangulationAlgorithm.class);

	/**
	 * Return a location computed from a map of CellSite/Strength
	 * 
	 * @param cellSites
	 *            Map with cell site as key and signal strength as value : At
	 *            least 3 cellSites are mandatory
	 * @return Location
	 * @see ITriangulationAlgorithm
	 */
	@Override
	public Location computeLocation(Map<CellSite, Integer> cellSites) {

		Validate.notNull(cellSites, "cellSites must not be null");
		Validate.validState(cellSites.size() >= 3,
				"At least 3 cell sites are mandatory", cellSites.size());

		int strengthSum = 0;
		int xSum = 0;
		int ySum = 0;

		Location cellLocation;
		int strength = 0;

		/*
		 * For each cell site / signal strength
		 */
		for (Entry<CellSite, Integer> entry : cellSites.entrySet()) {

			cellLocation = entry.getKey().getLocation();
			strength = entry.getValue();

			logger.debug(String
					.format("Compute location : cell site %s with strength=%d, x=%d, y=%d",
							entry.getKey().getId(), strength,
							cellLocation.getX(), cellLocation.getY()));

			xSum += strength * cellLocation.getX();
			ySum += strength * cellLocation.getY();

			strengthSum += strength;
		}
		int x = xSum / strengthSum;
		int y = ySum / strengthSum;

		logger.debug(String.format("Compute location : Result x=%d, y=%d", x, y));
		return new Location(x, y);
	}

}
