package eu.georget.triangulation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import eu.georget.triangulation.core.MobilePhone;
import eu.georget.triangulation.io.MobilePhoneDataReader;
import eu.georget.triangulation.io.MobilePhoneDataWriter;

/**
 * First Main App
 * 
 * Read data from the console : read stop with CTRL+D OR a blank line Mobile
 * phone locations are writed also to the console
 * 
 */
public class AppConsole {

	/**
	 * Logger
	 */
	static final Logger logger = Logger.getLogger(AppConsole.class);

	public static void main(String[] args) {

		MobilePhoneDataReader reader = null;

		try {

			InputStream in = System.in;
			reader = new MobilePhoneDataReader(in);
			
			/*
			 * Read data from console
			 */
			reader.readAll();

		} catch (IOException e) {
			logger.error("Error while reading input stream", e);
			return;
		} catch (IllegalArgumentException e) {
			logger.error("Format error in input stream", e);
			return;
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

		/*
		 * Get result
		 */
		List<MobilePhone> mobilePhones = reader.getMobilesPhones();

		MobilePhoneDataWriter writer = null;
		try {

			OutputStream out = System.out;
			writer = new MobilePhoneDataWriter(out);
			
			/*
			 * Write locations to console
			 */
			writer.writeMobilePhones(mobilePhones);

		} catch (IOException e) {
			logger.error("Error while writing output stream", e);

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
