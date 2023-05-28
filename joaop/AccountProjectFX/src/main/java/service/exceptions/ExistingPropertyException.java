package service.exceptions;
public class ExistingPropertyException extends Exception {

    private static String message = "%s property with '%s' mask already exists.";

    private static String formatWithExistingProperyName(String propertyName, String mask) {
        return String.format(message);
    }

    public ExistingPropertyException(String propertyName, String mask) {
        super(formatWithExistingProperyName(propertyName, mask));
    }
}
