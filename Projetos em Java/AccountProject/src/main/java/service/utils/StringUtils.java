package service.utils;

import service.exceptions.InvalidArgumentException;

public final class StringUtils {

    public static final String NOT_APPLICABLE = "not applicable";

    @Deprecated(since = "1.0")
    private StringUtils() { /* This class should not be instantiated. */ }

    public static void requireNonEmptyString(String string, String argument) throws InvalidArgumentException {
        if(string.equals("")) throw new InvalidArgumentException(argument, "[EMPTY]");
    }

}
