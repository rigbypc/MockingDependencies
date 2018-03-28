package log.examples;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


public class Log4jExamples {

	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void test() {
		logger.debug("Debug log");
		logger.info("Info log");
		logger.error("Error log");
	}

}
