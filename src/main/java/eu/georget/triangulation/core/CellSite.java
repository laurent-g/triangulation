package eu.georget.triangulation.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

/**
 * CellSite is a class which allow an application to represent a cell site. A
 * cell site has an id and a location.
 * 
 * @author laurent
 * 
 * @see ILocatable
 */
public class CellSite implements ILocatable {

	static final Logger logger = Logger.getLogger(CellSite.class);

	/**
	 * Id of the Cell site
	 */
	private String id;

	/**
	 * Location of the cell site
	 * 
	 * @see Location
	 */
	private Location location;

	/**
	 * Single constructor of CellSite
	 * 
	 * @param id
	 *            ID of the cell site
	 * @param location
	 *            Location of the cell site
	 */
	public CellSite(String id, Location location) {
		super();

		Validate.notNull(id, "Cell site id must not be null");
		Validate.notNull(location, "Cell site location must not be null");

		this.id = id;
		this.location = location;

		logger.debug(String.format("New CellSite : id=%s, x=%d, y=%d", id,
				location.getX(), location.getY()));
	}

	/**
	 * Return location of the cell site
	 * 
	 * @return location of the cell site
	 * @see Location
	 */
	@Override
	public Location getLocation() {
		return location;
	}

	/**
	 * Return id of the cell site
	 * 
	 * @return id of the cell site
	 */
	public String getId() {
		return id;
	}

	/**
	 * equals override : equal if ids are the same
	 * 
	 * @return true if objects are equals (same id)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CellSite) {
			CellSite otherCellSite = (CellSite) obj;
			return StringUtils.defaultString(id).equals(otherCellSite.getId());
		} else {
			return false;
		}
	}

	/**
	 * Overridden because this class is used in HashMap as key
	 * 
	 * @return hashcode of id
	 */
	@Override
	public int hashCode() {
		return StringUtils.defaultString(id).hashCode();
	}

	/**
	 * toString Override
	 * 
	 * @return "Cell site: ID"
	 */
	@Override
	public String toString() {
		return "Cell site: " + StringUtils.defaultString(id, "<null>");
	}

}
