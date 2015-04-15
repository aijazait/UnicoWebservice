package com.nihilent.controller;

import static com.nihilent.util.ApplicationConstants.LIST;
import static com.nihilent.util.ApplicationConstants.PUSH;
import static com.nihilent.util.ApplicationConstants.REST_URL;
import static com.nihilent.util.ApplicationConstants.SAPERATOR;

import javax.ws.rs.FormParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class RestController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }

    @RequestMapping(value = "/pushNumber", method = RequestMethod.POST)
    public String push(final @FormParam("firstNumber") String firstNumber, final @FormParam("secondNumber") String secondNumber, Model model) {
        logger.info("Start Method : -push ");
        RestTemplate restTemplate = new RestTemplate();
        String message =
                restTemplate.getForObject(REST_URL + PUSH + SAPERATOR + firstNumber + SAPERATOR + secondNumber, null, String.class);
        model.addAttribute("message", message);
        logger.info("Finish Method : -push ");
        return "index";
    }

    @RequestMapping("/pushNumber")
    public String list(Model model) {
        logger.info("Start Method : -push ");
        RestTemplate restTemplate = new RestTemplate();
        String message = restTemplate.getForObject(REST_URL + LIST, null, String.class);
        model.addAttribute("message", message);
        logger.info("Finish Method : -push ");
        return "index";
    }
}
