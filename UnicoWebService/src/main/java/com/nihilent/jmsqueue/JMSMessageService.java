package com.nihilent.jmsqueue;

import java.io.Serializable;
import java.util.List;

import com.nihilent.util.GreaterDenominatorVO;

/**
 * The Interface JMSMessageService.
 */
public interface JMSMessageService {

    /**
     * Read all messages.
     *
     * @return the list<? extends serializable>
     */
    List<? extends Serializable> readAllMessages();

    /**
     * Put gcd message queue.
     *
     * @param greaterDenominatorVO the greater denominator vo
     * @return true, if successful
     */
    boolean putGCDMessageQueue(GreaterDenominatorVO greaterDenominatorVO);

    /**
     * Gets the single message from queue.
     *
     * @return the single message from queue
     */
    GreaterDenominatorVO getSingleMessageFromQueue();

}
