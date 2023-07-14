package service.utils;

import service.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public final class StringUtils {

    public static final String NOT_APPLICABLE = "not applicable";

    @Deprecated(since = "1.0")
    private StringUtils() { /* This class should not be instantiated. */ }

    public static void requireNonNullArgs(String... args) {
        Arrays.stream(args).collect(Collectors.toList()).forEach(arg -> Objects.requireNonNull(arg));
    }

    public static void requireNonEmptyArgs(String... args) {
        Arrays.stream(args).collect(Collectors.toList()).forEach(arg -> {
            try {
                requireNonEmptyString(arg, "[Anonymous empty argument passed to requireNonEmptyArgs]");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
        });
    }
    
    public static void requireNonEmptyString(String string, String argumentName) throws InvalidArgumentException {
        if(string.equals("")) throw new InvalidArgumentException(argumentName, "[EMPTY]");
    }
}
