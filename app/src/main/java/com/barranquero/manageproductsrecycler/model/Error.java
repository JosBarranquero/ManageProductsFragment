package com.barranquero.manageproductsrecycler.model;

/**
 * Class which handles error codes
 */
public class Error {
    /**
     * Error codes
     */
    public static final int OK = 0;
    public static final int PASSWORD_DIGIT = 10;
    public static final int PASSWORD_CASE = 11;
    public static final int PASSWORD_LENGTH = 12;
    public static final int DATA_EMPTY = 13;
    public static final int EMAIL_INVALID = 14;

    public static String message;
    public static int code;

    static {

    }
}
