package eu.georget.triangulation.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import eu.georget.triangulation.core.CellSite;
import eu.georget.triangulation.core.Location;
import eu.georget.triangulation.core.MobilePhone;

/**
 * MobilePhoneDataReader
 * 
 * @author laurent
 * 
 */
public class MobilePhoneDataReader implements Closeable {

	static final Logger logger = Logger.getLogger(MobilePhoneDataReader.class);

	/**
	 * Regex for the first line : A number
	 */
	private static String FIRST_LINE_REGEX = "^\\d+$";

	/**
	 * Regex for the cell site lines : An id, x position and y position
	 * separated by a space
	 */
	private static String CELL_SITE_REGEX = "^([a-zA-Z]+)\\s+(\\d+)\\s+(\\d+)$";

	/**
	 * Regex for the mobile phone lines : A list of id of a cell site and signal
	 * strength
	 */
	private static String MOBILE_PHONE_REGEX = "^([a-zA-Z]+\\s+\\d+\\s+)+[a-zA-Z]+\\s+\\d+$";

	/**
	 * Regex to extract elements one by one in a the list
	 */
	private static String MOBILE_PHONE_FINDER_REGEX = "([a-zA-Z]+)\\s+(\\d+)";

	/**
	 * Reader used to read information
	 */
	BufferedReader reader;

	private List<CellSite> cellSites;
	private List<MobilePhone> mobilePhones;

	/**
	 * 
	 * @param stream
	 *            Stream used to read data
	 */
	public MobilePhoneDataReader(InputStream stream) {
		InputStreamReader sr = new InputStreamReader(stream);
		reader = new BufferedReader(sr);
	}

	/**
	 * Read data in stream
	 * 
	 * @throws IOException
	 *             Reading stream error
	 * @throws IllegalArgumentException
	 *             Incorrect stream format
	 */
	public void readAll() throws IOException, IllegalArgumentException {

		Matcher matcher;
		Map<String, CellSite> tmpCellSites = new HashMap<String, CellSite>();
		mobilePhones = new ArrayList<MobilePhone>();
		cellSites = new ArrayList<CellSite>();

		// First line
		logger.debug("Read first line");
		String strNumberOfCellSites = reader.readLine();
		Validate.matchesPattern(strNumberOfCellSites, FIRST_LINE_REGEX);
		int numberOfCellSites = Integer.valueOf(strNumberOfCellSites);

		// Cell site lines
		logger.debug("Read cell site lines");
		Pattern cellSitePattern = Pattern.compile(CELL_SITE_REGEX);
		String cellSiteLine;
		String cellSiteId;
		String strCellSiteX;
		String strCellSiteY;
		CellSite cellSite;

		for (int i = 0; i < numberOfCellSites; i++) {
			cellSiteLine = reader.readLine();
			Validate.matchesPattern(cellSiteLine, CELL_SITE_REGEX);

			matcher = cellSitePattern.matcher(cellSiteLine);

			if (matcher.matches()) {
				cellSiteId = matcher.group(1);
				strCellSiteX = matcher.group(2);
				strCellSiteY = matcher.group(3);

				cellSite = new CellSite(cellSiteId, new Location(
						Integer.valueOf(strCellSiteX),
						Integer.valueOf(strCellSiteY)));
				tmpCellSites.put(cellSiteId, cellSite);
				cellSites.add(cellSite);
			}
		}

		// Mobile phone lines
		logger.debug("Read mobile phone lines");
		Pattern mobilePhonePattern = Pattern.compile(MOBILE_PHONE_FINDER_REGEX);
		String mobilePhoneLine;
		MobilePhone mobilePhone;
		String strSignalStrength;

		while ((mobilePhoneLine = reader.readLine()) != null) {
			if ("".equals(mobilePhoneLine)) {
				break;
			}
			Validate.matchesPattern(mobilePhoneLine, MOBILE_PHONE_REGEX);

			mobilePhone = new MobilePhone();
			matcher = mobilePhonePattern.matcher(mobilePhoneLine);

			/*
			 * While there is something to read
			 */
			while (matcher.find()) {
				cellSiteId = matcher.group(1);
				strSignalStrength = matcher.group(2);

				if (tmpCellSites.containsKey(cellSiteId)) {
					mobilePhone.registerCellSite(tmpCellSites.get(cellSiteId),
							Integer.valueOf(strSignalStrength));
				}
			}
			mobilePhones.add(mobilePhone);
		}
	}

	/**
	 * Close connection
	 */
	@Override
	public void close() throws IOException {
		if (reader != null) {
			reader.close();
		}
	}

	/**
	 * Get cell sites as a result of readAll()
	 * 
	 * @return List of cell site in same order as reading
	 */
	public List<CellSite> getCellSites() {
		return cellSites;
	}

	/**
	 * Get mobiles sites as a result of readAll()
	 * 
	 * @return List of mobile phone in same order as reading
	 * 
	 * @see readAll
	 */
	public List<MobilePhone> getMobilesPhones() {
		return mobilePhones;
	}

}
