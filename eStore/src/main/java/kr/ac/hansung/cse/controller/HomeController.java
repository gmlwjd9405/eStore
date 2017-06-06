package kr.ac.hansung.cse.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {

		Logger logger = LoggerFactory.getLogger(HomeController.class);

		String url = request.getRequestURL().toString();
		String clientIPaddress = request.getRemoteAddr();

		logger.info("Request URL: " + url);
		logger.info("Client IP: " + clientIPaddress);

		// Logger logger = LoggerFactory.getLogger("kr.ac.hansung.cse.controller.HomeController");
		//
		// logger.trace("trace level: hello");
		// logger.debug("debug level: hello");
		// logger.info("info level: hello");
		// logger.warn("warn level: hello");
		// logger.error("error level: hello");

		return "home";
	}
}
