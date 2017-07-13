package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.model.ResultData;
import com.example.repository.ResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;


@Controller
public class DashBoardController {
	@Autowired
	private ResultRepository respository;

	@RequestMapping(value = {"/result" }, method = RequestMethod.GET)
	public ModelAndView orderLaptop(HttpServletRequest request) throws JsonProcessingException {
		List<ResultData> resultList = respository.findAll();
		ModelAndView view = new ModelAndView("Result");
		view.addObject("result", resultList);
		return view;
	}
}
