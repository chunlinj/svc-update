package org.daac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class HelloWorld {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

	@RequestMapping(method = RequestMethod.GET)
	public  String sayHello() {
		logger.debug("for test");
		return "Hello World";
	}

}
