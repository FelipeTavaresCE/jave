package br.com.jave.teste;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Hello {

	//static Logger logger = LoggerFactory.getLogger(Hello.class);

	public static void main(String argv[]) {
		Logger logger = LoggerFactory.getLogger(Hello.class);
		BasicConfigurator.configure();
		logger.debug("Hello world.");
		logger.info("What a beatiful day.");
	}
}