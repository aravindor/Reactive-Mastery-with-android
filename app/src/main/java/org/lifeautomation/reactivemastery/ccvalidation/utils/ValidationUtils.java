package org.lifeautomation.reactivemastery.ccvalidation.utils;

public class ValidationUtils {
    public static boolean checkExpirationDate(String date){
      return date.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
    }

    public static boolean checkCardChecksum(int[] digits) {
        int sum = 0;
        int length = digits.length;
        for (int i = 0; i < length; i++) {
// Get digits in reverse order 1
            int digit = digits[length - i - 1];
// Every 2nd number multiply with 2
            if (i % 2 == 1) {
                digit *= 2;
            }
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum % 10 == 0;
    }
}
