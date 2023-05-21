package service.services.properties;

import service.exceptions.ExistingPropertyException;
import service.exceptions.PropertyNotFoundException;
import service.managers.PropertiesManager;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public final class DateTimeProperties {

    private static final String path = "./properties/datetime.properties.txt";

    @Deprecated
    private DateTimeProperties() {
    }

    public static ZoneId getDefaultZoneId() throws IOException, PropertyNotFoundException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);
        return ZoneId.of(propManager.getProperty(properties, "default.datetime.zoneId"));
    }

    public static TimeZone getDefaultTimeZone() throws PropertyNotFoundException, IOException {
        return TimeZone.getTimeZone(getDefaultZoneId());
    }

    public static Locale getDefaultLocale() throws IOException, PropertyNotFoundException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);
        return Locale.forLanguageTag(propManager.getProperty(properties, "default.locale.language.tag"));
    }

    public static SimpleDateFormat getDefaultSimpleDateFormat() throws IOException, PropertyNotFoundException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);
        return new SimpleDateFormat(propManager.getProperty(properties, "default.simple.date.format"));
    }

    public static void setCustomZoneId(String host, String ZoneId) throws IOException, ExistingPropertyException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        String mask = String.format("custom.%s.datetime.zoneID", host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, ZoneId);
    }

    public static void setCustomLocale(String host, String languageTag) throws IOException, ExistingPropertyException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        String mask = String.format("custom.%s.locale.language.tag", host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, languageTag);
    }

    public static void setCustomSimpleDateFormat(String host, String format) throws IOException, ExistingPropertyException {
        PropertiesManager propManager = new PropertiesManager();
        Properties properties = propManager.loadProperties(path);

        String mask = String.format("custom.%s.simple.date.format", host);
        if(properties.getProperty(mask) != null) throw new ExistingPropertyException("DateTime", mask);

        propManager.setProperty(properties, mask, format);
    }

}
