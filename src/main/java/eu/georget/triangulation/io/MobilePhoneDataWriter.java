package eu.georget.triangulation.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.log4j.Logger;

import eu.georget.triangulation.core.Location;
import eu.georget.triangulation.core.MobilePhone;

/**
 * MobilePhoneDataWriter allow to print to outputstream the location of a list
 * of mobile phones
 * 
 * @author laurent
 * 
 */
public class MobilePhoneDataWriter implements Closeable {

	/**
	 * Logger
	 */
	static final Logger logger = Logger.getLogger(MobilePhoneDataWriter.class);

	/**
	 * Writer used
	 */
	BufferedWriter writer;

	/**
	 * 
	 * @param stream
	 *            Outputstream where the class will write
	 */
	public MobilePhoneDataWriter(OutputStream stream) {
		super();
		this.writer = new BufferedWriter(new OutputStreamWriter(stream));
	}

	/**
	 * 
	 * @param mobilePhones
	 *            List with mobile phones
	 * @throws IOException
	 * 
	 * @see MobilePhone
	 */
	public void writeMobilePhones(List<MobilePhone> mobilePhones)
			throws IOException {

		Location location;
		for (MobilePhone mobilePhone : mobilePhones) {
			location = mobilePhone.getLocation();
			logger.debug(String.format("Location : x=%d, y=%d",
					location.getX(), location.getY()));
			writer.write(String.format("%d %d", location.getX(),
					location.getY()));
			writer.newLine();
		}
	}

	/**
	 * Close connection
	 */
	@Override
	public void close() throws IOException {
		if (writer != null) {
			writer.close();
		}
	}

}
