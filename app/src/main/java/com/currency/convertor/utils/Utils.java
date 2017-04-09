package com.currency.convertor.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * App utility class
 *
 * Created by Renjith Kandanatt on 08/04/2017.
 */

public class Utils {
    /**
     * Generates a random UUID
     * @return a valid identifier of {@link UUID} type
     */
    public static UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    /**
     * Checks if the list is safe to iterate
     * @param list a valid list to be checked
     * @return empty list if it's null or empty, otherwise the actual list is returned
     */
    public static <T> List<T> safeList(List<T> list) {
        return null == list ? Collections.EMPTY_LIST : list;
    }

    /**
     * Checks whether a list is empty or not
     * @param list list of any type
     * @return true if the list is empty and false otherwise
     */
    public static boolean isEmptyList(List<?> list) {
        return null == list || list.isEmpty();
    }

    /**
     * Formats a number in the desired pattern
     * Currently used pattern is #,###.00
     * Applies to all currency values
     *
     * @param inputValue value to be formatted
     * @return formatted value and original value if format operation fails
     */
    public static String formatNumber(BigDecimal inputValue) {
        try{

            inputValue = inputValue.setScale(2, BigDecimal.ROUND_DOWN);
            DecimalFormat formatter = new DecimalFormat("#,##0.00");
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            return formatter.format(inputValue);
        } catch(Exception ex) {
            ex.printStackTrace();
            return inputValue.toString();
        }
    }
}
