package com.nihilent.webservicecontroller;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.nihilent.jmsqueue.JMSMessageService;
import com.nihilent.util.GreaterDenominatorVO;

/**
 * The Class RestWebServiceController.
 */
@RestController
@ControllerAdvice
@EnableWebMvc
public class RestWebServiceController {

    /** The jms message service. */
    @Autowired
    JMSMessageService jmsMessageService;

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(RestWebServiceController.class);

    /**
     * Push.
     *
     * @param param1 the param1
     * @param param2 the param2
     * @return the string
     */
    @RequestMapping(value = "/push/{param1}/{param2}", method = RequestMethod.GET)
    @Produces("application/json")
    public String push(@PathVariable("param1") String param1, @PathVariable("param2") String param2) {
        logger.info("Start Method : -push ");
        String message = null;
        GreaterDenominatorVO denominatorVO = converIntoObject(param1, param2);
        if (denominatorVO != null) {
            boolean pushMessageOnQueue = jmsMessageService.putGCDMessageQueue(denominatorVO);
            if (pushMessageOnQueue) {
                System.out.println("Message Posted");
                message = "Successfuly inserted into queue.";
            } else {
                message = "There is some problem, Please contact administrator";
            }
        } else {
            message = "Faild to insert, Please provide two integer.";
        }
        logger.info("Finish Method: -push ");
        return message;
    }

    /**
     * List.
     *
     * @return the string
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Produces("application/json")
    public String list() {
        logger.info("Start Method : -push ");
        List<GreaterDenominatorVO> readAllMessages = (List<GreaterDenominatorVO>) jmsMessageService.readAllMessages();
        Gson gson = new Gson();
        String allElement = gson.toJson(readAllMessages);
        logger.info("Finish Method: -push ");
        return allElement;
    }

    private GreaterDenominatorVO converIntoObject(String param1, String param2) {

        GreaterDenominatorVO denominatorVO = null;
        if (param1 != null && !param1.isEmpty() && param2 != null && !param2.isEmpty()) {
            try {
                denominatorVO = new GreaterDenominatorVO(new BigInteger(param1), new BigInteger(param2));
            } catch (NumberFormatException exception) {
                logger.error("Got Number format exception" + exception.getMessage());
            }
        }
        return denominatorVO;
    }
}
