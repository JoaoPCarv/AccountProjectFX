package service.exceptions;
public class PropertyNotFoundException extends Exception {

    private static String message = "Property '%s' does not exist.";

    private static String formatWithExistingPropertyName(String propertyName) {
        return String.format(message, propertyName);
    }

    public PropertyNotFoundException(String propertyName) {
        super(formatWithExistingPropertyName(propertyName));
    }

}
