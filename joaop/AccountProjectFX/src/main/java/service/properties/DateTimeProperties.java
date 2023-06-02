package service.properties;

import service.exceptions.ExistingPropertyException;
import service.exceptions.PropertyNotFoundException;
import service.managers.PropertiesManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public final class DateTimeProperties {

    private static final String propResource = "/datetime.properties";
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

    public static ZoneId getDefaultZoneId() throws IOException, PropertyNotFoundException, URISyntaxException {
        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);
        return ZoneId.of(propManager.getProperty(properties, DEFAULT_ZONE_ID_MASK));
    }

    public static TimeZone getDefaultTimeZone() throws PropertyNotFoundException, IOException, URISyntaxException {
        return TimeZone.getTimeZone(getDefaultZoneId());
    }

    public static Locale getDefaultLocale() throws IOException, PropertyNotFoundException, URISyntaxException {
        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);
        return Locale.forLanguageTag(propManager.getProperty(properties, DEFAULT_LOCALE_MASK));
    }

    public static SimpleDateFormat getDefaultSimpleDateFormat() throws IOException,
            PropertyNotFoundException, URISyntaxException {
        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);
        return new SimpleDateFormat(propManager.getProperty(properties, DEFAULT_SIMPLE_DATE_FORMAT_MASK));
    }

    public static void setCustomZoneId(String host, String ZoneId) throws IOException,
            ExistingPropertyException, URISyntaxException {
        validateArguments(host, ZoneId);
        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);

        String mask = String.format(CUSTOM_ZONE_ID_MASK, host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, ZoneId, propResource);
    }

    public static void setCustomLocale(String host, String languageTag) throws IOException,
            ExistingPropertyException, URISyntaxException {
        validateArguments(host, languageTag);
        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);

        String mask = String.format(CUSTOM_LOCALE_MASK, host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, languageTag, propResource);
    }

    public static void setCustomSimpleDateFormat(String host, String format) throws IOException,
            ExistingPropertyException, URISyntaxException {
        validateArguments(host, format);
        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);

        String mask = String.format(CUSTOM_SIMPLE_DATE_FORMAT_MASK, host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, format, propResource);
    }

    private static String getCustomDateTimeProperty(String host, String mask)
            throws IOException, PropertyNotFoundException, URISyntaxException {
        validateArguments(host);

        PropertiesManager propManager = PropertiesManager.getInstance();
        Properties properties = propManager.loadPropertiesFromResourceName(propResource);

        return propManager.getProperty(properties, String.format(mask, host));
    }

    public static ZoneId getCustomZoneId(String host) throws IOException,
            PropertyNotFoundException, URISyntaxException {
        return ZoneId.of(getCustomDateTimeProperty(host, CUSTOM_ZONE_ID_MASK));
    }

    public static TimeZone getCustomTimeZone(String host) throws IOException,
            PropertyNotFoundException, URISyntaxException {
        return TimeZone.getTimeZone(getCustomZoneId(host));
    }

    public static Locale getCustomLocale(String host) throws IOException, PropertyNotFoundException,
            URISyntaxException {
        return Locale.forLanguageTag(getCustomDateTimeProperty(host, CUSTOM_LOCALE_MASK));
    }

    public static SimpleDateFormat getCustomSimpleDateFormat(String host) throws IOException,
            PropertyNotFoundException, URISyntaxException {
        return new SimpleDateFormat(getCustomDateTimeProperty(host, CUSTOM_SIMPLE_DATE_FORMAT_MASK));
    }
}
