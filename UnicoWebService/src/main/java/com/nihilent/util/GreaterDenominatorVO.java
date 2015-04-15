package com.nihilent.util;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * The Class GreaterDenominatorVO.
 */
public class GreaterDenominatorVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 346869550395617729L;

    /** The first integer. */
    private BigInteger firstInteger;

    /** The second integer. */
    private BigInteger secondInteger;

    /** The gcd number. */
    private BigInteger gcdNumber;

    /**
     * Instantiates a new greater denominator vo.
     *
     * @param one the one
     * @param two the two
     */
    public GreaterDenominatorVO(BigInteger one, BigInteger two) {
        this.firstInteger = one;
        this.secondInteger = two;
    }

    /**
     * Gets the first integer.
     *
     * @return the first integer
     */
    public BigInteger getFirstInteger() {
        return firstInteger;
    }

    /**
     * Gets the second integer.
     *
     * @return the second integer
     */
    public BigInteger getSecondInteger() {
        return secondInteger;
    }

    /**
     * Gets the gcd number.
     *
     * @return the gcd number
     */
    public BigInteger getGcdNumber() {
        return gcdNumber;
    }

    /**
     * Sets the first integer.
     *
     * @param firstInteger the new first integer
     */
    public void setFirstInteger(BigInteger firstInteger) {
        this.firstInteger = firstInteger;
    }

    /**
     * Sets the second integer.
     *
     * @param secondInteger the new second integer
     */
    public void setSecondInteger(BigInteger secondInteger) {
        this.secondInteger = secondInteger;
    }

    /**
     * Sets the gcd number.
     *
     * @param gcdNumber the new gcd number
     */
    public void setGcdNumber(BigInteger gcdNumber) {
        this.gcdNumber = gcdNumber;
    }

}
