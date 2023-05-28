package service.services.serializing;

import model.beans.Bean;
import service.exceptions.InvalidArgumentException;
import service.exceptions.PropertyNotFoundException;
import service.managers.SerializingManager;
import service.properties.BeanProperties;
import service.services.datetime.DateTimeService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class SerializingService {

    private static final String SER = "Serialization";
    private static final String DESER = "Deserialization";

    @Deprecated(since = "1.0")
    private SerializingService() { /* This class should not be instantiated. */ }

    public static <B extends Bean> String getThisBeanSerializingPath(Class<B> clazzB, int hashCode) throws IOException {
        return BeanProperties.getBeanDirectoryPath(clazzB) + hashCode + ".txt";
    }

    public static <B extends Bean> void serializeBean(B bean, String type, String host) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(getThisBeanSerializingPath(bean.getClass(), bean.hashCode())))) {
            bean.setLastSerializationDateTime(DateTimeService.getFormattedDateTimeWithZoneIdAndLocale(type, host));
            SerializingManager.writeObject(bean, out);
            logBeanSerializingProcess(SER, bean.getClass(), type, host);
        } catch (PropertyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static <B extends Bean> B deserializeBean
            (Class<B> clazzB, int hashCode, String type, String host) {
        try(ObjectInputStream in  = new ObjectInputStream(
                new FileInputStream(getThisBeanSerializingPath(clazzB, hashCode)))){
            Serializable ser = SerializingManager.readObject(in);
            if(!(ser instanceof Bean)) {
                throw new ClassCastException("Serialized object is not a Bean.");
            }
            logBeanSerializingProcess(DESER, clazzB, type, host);
            return (B) ser;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        } catch (PropertyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static <B extends Bean> void logBeanSerializingProcess
            (String operation, Class<B> clazz, String type, String host)
            throws IOException, InvalidArgumentException, PropertyNotFoundException {

        if(!operation.equals(SER) && !operation.equals(DESER)) {
            throw new InvalidArgumentException("Serialization Operation", operation);
        }

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(operation);
        sBuilder.append(" of a Bean(");
        sBuilder.append(clazz.getSimpleName());
        sBuilder.append(") requested {");
        sBuilder.append("Time: [");
        sBuilder.append(DateTimeService.getFormattedDateTimeWithZoneIdAndLocale(type, host));
        sBuilder.append("]; ");
        sBuilder.append("Host: [");
        sBuilder.append(host);
        sBuilder.append("]}. \n");

        Files.write(Paths.get("./src/main/java/repository/serializing/logs/BeanSerializing.txt"),
                sBuilder.toString().getBytes(), StandardOpenOption.APPEND);
    }
}