package service.properties;

import service.exceptions.ExistingPropertyException;
import service.exceptions.PropertyNotFoundException;
import service.managers.PropertiesManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public final class DateTimeProperties {

    private static final String path = "./properties/datetime.properties";
    // Masks
    private static final String DEFAULT_ZONE_ID_MASK = "default.datetime.zoneId";
    private static final String DEFAULT_LOCALE_MASK = "default.locale.language.tag";
    private static final String DEFAULT_SIMPLE_DATE_FORMAT_MASK = "default.simple.date.format";
    private static final String CUSTOM_ZONE_ID_MASK = "custom.%s.datetime.zoneId";
    private static final String CUSTOM_LOCALE_MASK = "custom.%s.locale.language.tag";
    private static final String CUSTOM_SIMPLE_DATE_FORMAT_MASK = "custom.%s.simple.date.format";

    //

    @Deprecated(since = "1.0")
    private DateTimeProperties() { /* This class should not be instantiated. */ }

    private static void validateArguments(String... args) {
        Arrays.stream(args).collect(Collectors.toList()).forEach(arg -> Objects.requireNonNull(arg));
    }

    public static ZoneId getDefaultZoneId() throws IOException, PropertyNotFoundException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);
        return ZoneId.of(propManager.getProperty(properties, DEFAULT_ZONE_ID_MASK));
    }

    public static TimeZone getDefaultTimeZone() throws PropertyNotFoundException, IOException {
        return TimeZone.getTimeZone(getDefaultZoneId());
    }

    public static Locale getDefaultLocale() throws IOException, PropertyNotFoundException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);
        return Locale.forLanguageTag(propManager.getProperty(properties, DEFAULT_LOCALE_MASK));
    }

    public static SimpleDateFormat getDefaultSimpleDateFormat() throws IOException, PropertyNotFoundException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);
        return new SimpleDateFormat(propManager.getProperty(properties, DEFAULT_SIMPLE_DATE_FORMAT_MASK));
    }

    public static void setCustomZoneId(String host, String ZoneId) throws IOException, ExistingPropertyException {
        validateArguments(host, ZoneId);
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        String mask = String.format(CUSTOM_ZONE_ID_MASK, host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, ZoneId, path);
    }

    public static void setCustomLocale(String host, String languageTag) throws IOException, ExistingPropertyException {
        validateArguments(host, languageTag);
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        String mask = String.format(CUSTOM_LOCALE_MASK, host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, languageTag, path);
    }

    public static void setCustomSimpleDateFormat(String host, String format) throws IOException, ExistingPropertyException {
        validateArguments(host, format);
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        String mask = String.format(CUSTOM_SIMPLE_DATE_FORMAT_MASK, host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, format, path);
    }

    private static String getCustomDateTimeProperty(String host, String mask)
            throws IOException, PropertyNotFoundException {
        validateArguments(host);

        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        return propManager.getProperty(properties, String.format(mask, host));
    }

    public static ZoneId getCustomZoneId(String host) throws IOException, PropertyNotFoundException {
        return ZoneId.of(getCustomDateTimeProperty(host, CUSTOM_ZONE_ID_MASK));
    }

    public static TimeZone getCustomTimeZone(String host) throws IOException, PropertyNotFoundException {
        return TimeZone.getTimeZone(getCustomZoneId(host));
    }

    public static Locale getCustomLocale(String host) throws IOException, PropertyNotFoundException {
        return Locale.forLanguageTag(getCustomDateTimeProperty(host, CUSTOM_LOCALE_MASK));
    }

    public static SimpleDateFormat getCustomSimpleDateFormat(String host) throws IOException, PropertyNotFoundException {
        return new SimpleDateFormat(getCustomDateTimeProperty(host, CUSTOM_SIMPLE_DATE_FORMAT_MASK));
    }
}
