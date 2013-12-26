package eu.georget.triangulation.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import eu.georget.triangulation.core.CellSite;
import eu.georget.triangulation.core.MobilePhone;
import eu.georget.triangulation.io.MobilePhoneDataReader;
import eu.georget.triangulation.io.MobilePhoneDataWriter;


public class TestIO {

	static final Logger logger = Logger.getLogger(TestIO.class);

	public TestIO() {
	}

	@Test
	public void testReadAndWriteFile() {
		MobilePhoneDataReader reader = null;

		try {

			InputStream in = getClass().getResourceAsStream("/input.txt");
			reader = new MobilePhoneDataReader(in);
			reader.readAll();

		} catch (IOException e) {
			logger.error("Error while reading input stream", e);
			Assert.fail("Error while reading input stream");
		} catch (IllegalArgumentException e) {
			logger.error("Format error in input stream", e);
			Assert.fail("Format error in input stream");
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// ignore ... any significant errors should already have been
				// reported via an IOException
			}
		}

		Assert.assertNotNull(reader);

		List<CellSite> cellSites = reader.getCellSites();
		Assert.assertNotNull("Cell sites should not be null", cellSites);
		Assert.assertEquals("It should be 4 cell sites", cellSites.size(), 4);

		List<MobilePhone> mobilePhones = reader.getMobilesPhones();
		Assert.assertNotNull(mobilePhones);

		Assert.assertNotNull("Mobile phones should not be null", cellSites);
		Assert.assertEquals("It should be 2 mobile phones", cellSites.size(), 4);

		MobilePhoneDataWriter writer = null;
		try {

			OutputStream out = System.out;
			writer = new MobilePhoneDataWriter(out);
			writer.writeMobilePhones(mobilePhones);

		} catch (IOException e) {
			logger.error("Error while writing output stream", e);
			Assert.fail("Error while writing output stream");

		} finally {

			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// ignore ... any significant errors should already have been
				// reported via an IOException
			}
		}

	}
}
