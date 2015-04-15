package com.nihilent.webservicecontroller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.nihilent.jmsqueue.JMSMessageService;
import com.nihilent.util.GreaterDenominatorVO;

/**
 * The Class SoapWebServiceController.
 */
@WebService(serviceName = "SoapWebServiceController")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class SoapWebServiceController extends SpringBeanAutowiringSupport {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(SoapWebServiceController.class);

    /** The jms message service. */
    @Autowired
    JMSMessageService jmsMessageService;

    /**
     * Gets the gcd.
     * @return the gcd
     */
    @WebMethod
    public String getGCD() {
        logger.info("Start Method : -getGCD ");
        GreaterDenominatorVO singleMessageFromQueue = jmsMessageService.getSingleMessageFromQueue();
        BigInteger gcdValue = this.processGCD(singleMessageFromQueue.getFirstInteger(), singleMessageFromQueue.getSecondInteger());
        if (gcdValue != null) {
            singleMessageFromQueue.setGcdNumber(gcdValue);
        }
        jmsMessageService.putGCDMessageQueue(singleMessageFromQueue);
        logger.info("Finish Method : -getGCD ");
        return String.valueOf(singleMessageFromQueue.getGcdNumber());
    }

    /**
     * Gets the sum of gcd.
     * @return the sum of gcd
     */
    @WebMethod
    public String getSumOfGCD() {
        List<GreaterDenominatorVO> readAllMessages = (List<GreaterDenominatorVO>) jmsMessageService.readAllMessages();
        BigInteger bigInteger = new BigInteger("0");
        for (GreaterDenominatorVO greaterDenominatorVO : readAllMessages) {
            if (greaterDenominatorVO.getGcdNumber() != null) {
                bigInteger = bigInteger.add(greaterDenominatorVO.getGcdNumber());
            }
        }
        return String.valueOf(bigInteger);
    }

    /**
     * Gets the gcd list.
     * @return the gcd list
     */
    @WebMethod
    public List<String> getGcdList() {
        List<GreaterDenominatorVO> readAllMessages = (List<GreaterDenominatorVO>) jmsMessageService.readAllMessages();
        List<String> list = new ArrayList<String>();
        for (GreaterDenominatorVO greaterDenominatorVO : readAllMessages) {
            if (greaterDenominatorVO.getGcdNumber() != null) {
                list.add(String.valueOf(greaterDenominatorVO.getGcdNumber()));
            }
        }
        return list;
    }

    /**
     * Process gcd.
     */
    private BigInteger processGCD(BigInteger firstInteger, BigInteger secondInteger) {
        BigInteger gcdNumber = null;
        if (firstInteger != null && secondInteger != null) {
            gcdNumber = firstInteger.gcd(secondInteger);
        }
        return gcdNumber;

    }
}
