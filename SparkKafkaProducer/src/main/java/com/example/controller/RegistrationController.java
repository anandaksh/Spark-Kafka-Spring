package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.KafkaInputMessage;
import com.example.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RestController
public class RegistrationController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private KafkaService kafkaSenderService;

	@RequestMapping(value = "/records", method = RequestMethod.GET)
	public ModelAndView orderLaptop(HttpServletRequest request) throws JsonProcessingException {
		logger.info("Fetching registration page ");
		ModelAndView model = new ModelAndView("Registration");
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Map<String, Object>> placeOrder(@RequestBody KafkaInputMessage kakfaInputOrderMessage,
			final HttpServletRequest request, final HttpServletResponse response)
			throws ServletRequestBindingException, SQLException, IOException, InterruptedException, ExecutionException {
		logger.info("doing registration ");
		kafkaSenderService.sendMessageToKafkaTopic(kakfaInputOrderMessage);
		logger.info("Done with messaging using Kafka");
		Map<String, Object> ret = new HashMap<>();
		ret.put("STATUS", "SUCCESS");
		return new ResponseEntity<Map<String, Object>>(ret, HttpStatus.OK);
	}
}