package users.business.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NormalizeUtils {

    public static String normalizeCep(String zipCode) {
        return zipCode.replaceAll("[^0-9]", "").trim();
    }

    public static String normalizeString(String value) {
        return value.toLowerCase();
    }

    public static String trim(String value) {
        return value.trim();
    }

}
