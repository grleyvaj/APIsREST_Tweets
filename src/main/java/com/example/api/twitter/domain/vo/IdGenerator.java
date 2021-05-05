package com.example.api.twitter.domain.vo;

/**
 * @author Gloria R. Leyva Jerez
 * Contains useful functionalities for generating identifiers
 */
public class IdGenerator {

//    public static final int ID_LENGTH = 9;
//
//    public static int generateUniqueId() {
//        return Integer.parseInt(RandomStringUtils.randomNumeric(ID_LENGTH));
//    }

    private static long idCounter = 0;

    /**
     * @return Identifier generated
     */
    public static synchronized long generateUniqueId() {
        return idCounter++;
    }
}
