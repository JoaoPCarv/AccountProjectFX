package service.services.datetime;

import service.exceptions.ExistingPropertyException;
import service.exceptions.InvalidArgumentException;
import service.exceptions.PropertyNotFoundException;
import service.properties.DateTimeProperties;
import service.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

public final class DateTimeService {

    public static final String DEFAULT = "default";
    public static final String CUSTOM = "custom";

    @Deprecated(since = "1.0")
    public DateTimeService() { /* This class should not be instantiated. */ }

    private static void validateArguments(String host, String argName, String argValue)
            throws InvalidArgumentException {
        Objects.requireNonNull(host, "Host can not be null.");
        Objects.requireNonNull(argValue, String.format("%s can not be null.", argName));

        StringUtils.requireNonEmptyString(host, "Host");
        StringUtils.requireNonEmptyString(argValue, argName);
    }

    private static List<String> loadDateTimeList(String argListName) throws FileNotFoundException {
        try(BufferedReader bReader = new BufferedReader(
                new FileReader("./src/main/resources/DATE-TIME/" + argListName + ".txt"))) {

            List<String> dateTimeList = new ArrayList<>();
            String line = bReader.readLine();
            do {
                dateTimeList.add(line);
                line = bReader.readLine();
            } while(line != null);

            return dateTimeList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFormattedDefaultDateTime() throws PropertyNotFoundException, IOException {

        return DateTimeProperties.getDefaultSimpleDateFormat().format(
                Calendar.getInstance(DateTimeProperties.getDefaultTimeZone(), DateTimeProperties.getDefaultLocale())
                .getTime());
    }

    private static void validateList(String host, String argName, String argValue, String argListName)
            throws FileNotFoundException, InvalidArgumentException {
        validateArguments(host, argName, argValue);
        List<String> allAvailableList = loadDateTimeList(argListName);
        if(allAvailableList.stream().noneMatch(line -> line.equals(argValue)))
            throw new InvalidArgumentException(argName, argValue);
    }

    public static void registerCustomZoneId(String host, String zoneId)
            throws IOException, InvalidArgumentException, ExistingPropertyException {
        validateList(host, "ZoneId", zoneId, "AllAvailableZoneIds");
        DateTimeProperties.setCustomZoneId(host, zoneId);

    }

    public static void registerCustomLocale(String host, String languageTag)
            throws InvalidArgumentException, IOException, ExistingPropertyException {
        validateList(host, "Locale language tag", languageTag, "AllAvailableLocales");
        DateTimeProperties.setCustomLocale(host, languageTag);
    }

    public static void registerCustomSimpleDateFormat(String host, String format) throws InvalidArgumentException {
        validateArguments(host, "Format", format);

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            DateTimeProperties.setCustomSimpleDateFormat(host, format);
        } catch (IllegalArgumentException ex) {
            throw new InvalidArgumentException("Simple Date Format", format);
        } catch (ExistingPropertyException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getFormattedDateTimeWithZoneIdAndLocale(String type, String host)
            throws PropertyNotFoundException, IOException, InvalidArgumentException {
        Objects.requireNonNull(type);

        if (type.equals(DEFAULT)) {
            return DateTimeProperties.getDefaultSimpleDateFormat().format(
                    Calendar.getInstance(DateTimeProperties.getDefaultTimeZone(),
                            DateTimeProperties.getDefaultLocale()).getTime());
        } else if(type.equals(CUSTOM)) {

            TimeZone timeZone;
            try {
                timeZone = DateTimeProperties.getCustomTimeZone(host);
            } catch(PropertyNotFoundException ex) {
                timeZone = DateTimeProperties.getDefaultTimeZone();
            }

            Locale locale;
            try {
                locale = DateTimeProperties.getCustomLocale(host);
            } catch(PropertyNotFoundException ex) {
                locale = DateTimeProperties.getDefaultLocale();
            }

            SimpleDateFormat simpleDateFormat;
            try {
                simpleDateFormat = DateTimeProperties.getCustomSimpleDateFormat(host);
            } catch(PropertyNotFoundException ex) {
                simpleDateFormat = DateTimeProperties.getDefaultSimpleDateFormat();
            }

            return simpleDateFormat.format(Calendar.getInstance(timeZone, locale).getTime());

        } else throw new InvalidArgumentException("Type [DateTime: default | custom]", "type");
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Pacific/Kosrae")),
                Locale.forLanguageTag("pt-BR")).getTime());
    }
}