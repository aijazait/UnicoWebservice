package com.nihilent.jmsqueue;

import static com.nihilent.util.ApplicationConstants.GCD_INPUT_QUEUE_NAME;
import static com.nihilent.util.ApplicationConstants.JMS_CONNECTION_FACTORY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nihilent.util.GreaterDenominatorVO;

/**
 * This class is used to perform operation on queue like push on queue, retrieve from queue, updating queue.
 *
 * @param <T> the generic type
 */
@Service(value = JMSMessageServiceImpl.SERVICE_NAME)
public class JMSMessageServiceImpl<T extends Serializable> implements JMSMessageService {

    /** The Constant SERVICE_NAME. */
    public static final String SERVICE_NAME = "jmsMessageService";

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(JMSMessageServiceImpl.class);

    /** The connection factory. */
    private ConnectionFactory connectionFactory = null;

    /** The gcd input queue. */
    private Queue gcdInputQueue;

    /** The session. */
    Session session;

    /** The connection. */
    Connection connection;

    /** The jms template. */
    @Autowired
    JmsTemplate jmsTemplate;

    /**
    * This method is used to read all message available in queue.
    * @return List of GreaterDenominatorVO
    * 
    */
    @Override
    public List<? extends Serializable> readAllMessages() {
        logger.info("Start Method : -readAllMessages ");
        _initialize();
        List<T> list = new ArrayList<T>();
        try {
            QueueBrowser browser = session.createBrowser(gcdInputQueue);
            Enumeration enumeration = browser.getEnumeration();
            while (enumeration.hasMoreElements()) {
                ObjectMessage objectMessage = (ObjectMessage) enumeration.nextElement();
                GreaterDenominatorVO denominatorVO = (GreaterDenominatorVO) objectMessage.getObject();
                list.add((T) denominatorVO);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
        logger.info("Finish Method : -readAllMessages ");
        return list;
    }

    /**
     * This method is used to initialize queue.
     *
     * @return the initial context
     */
    private void _initialize() {
        logger.info("Start Method : -_initialize ");
        InitialContext context = null;
        try {
            context = new InitialContext();
            connectionFactory = (QueueConnectionFactory) context.lookup(JMS_CONNECTION_FACTORY);
            if (context != null) {
                this.gcdInputQueue = (Queue) context.lookup(GCD_INPUT_QUEUE_NAME);
                this.connection = connectionFactory.createConnection("guest", "guest");
                this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }
        } catch (NamingException exception) {
            logger.error(exception.getMessage());
        } catch (JMSException exception) {
            exception.printStackTrace();
            logger.error(exception.getMessage());
        }
        logger.info("Finish Method : -_initialize ");
    }

    /**
     * This method is used to put GreaterDenominatorVO object into queue.
     * @return boolean
     */
    public boolean putGCDMessageQueue(GreaterDenominatorVO greaterDenominatorVO) {
        logger.info("Start Method : -putGCDMessageQueue ");
        jmsTemplate.convertAndSend(greaterDenominatorVO);
        logger.info("Finish Method : -putGCDMessageQueue ");
        return true;
    }

    /** 
     * This method is used to get single message from queue.
     * @return GreaterDenominatorVO
     */
    public GreaterDenominatorVO getSingleMessageFromQueue() {
        logger.info("Start Method : -getSingleMessageFromQueue ");
        GreaterDenominatorVO denominatorVO = (GreaterDenominatorVO) jmsTemplate.receiveAndConvert();
        logger.info("Finish Method : -getSingleMessageFromQueue ");
        return denominatorVO;

    }
}
