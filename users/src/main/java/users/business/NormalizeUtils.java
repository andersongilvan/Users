package users.business;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NormalizeUtils {

    public static String normalizeCep(String zipCode) {
        return zipCode.replaceAll("[^0-9]", "");
    }

    public static String normalizeString(String value) {
        return value.toLowerCase();
    }

}
