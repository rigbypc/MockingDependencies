package log.examples;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


public class Log4jMultipleLoggersExamples {

	private static Logger rootLog = LogManager.getLogger();
	private static Logger consoleLog = LogManager.getLogger("analytics.console");
	private static Logger fileLog = LogManager.getLogger("analytics");
	
	@Test
	public void testRoot() {
		System.out.println("Root logger");
		rootLog.debug("Debug log");
		rootLog.info("Info log");
		rootLog.error("Error log");
				
	}
	
	//@Test
	public void testFile() {
		System.out.println("File Logger");
		fileLog.debug("Debug log");
		fileLog.info("Info log");
		fileLog.error("Error log");
				
	}
	
	//@Test
	public void testConsole() {
		System.out.println("Console Logger");
		consoleLog.debug("Debug log");
		consoleLog.info("Info log");
		consoleLog.error("Error log");
	}

}
