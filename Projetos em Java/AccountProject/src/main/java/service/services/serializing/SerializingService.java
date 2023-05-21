package service.serializingService;

import model.beans.Bean;
import service.propertiesService.BeanProperties;

import java.io.*;

public final class SerializingService {

    @Deprecated
    private SerializingService() {}

    public static <B extends Bean> String getThisBeanSerializingPath(Class<B> bean, int hashCode) throws IOException {
        return BeanProperties.getBeanDirectoryPath(bean) + hashCode + ".txt";
    }

    public static <B extends Bean> void serializeBean(B bean) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(getThisBeanSerializingPath(bean.getClass(), bean.hashCode())))) {
            SerializingManager.writeObject(bean, out);
        }
    }

    public static <B extends Bean> B deserializeBean(Class<B> bean, int hashCode) {
        try(ObjectInputStream in  = new ObjectInputStream(
                new FileInputStream(getThisBeanSerializingPath(bean, hashCode)))){
            Serializable ser = SerializingManager.readObject(in);
            if(!(ser instanceof Bean)) {
                throw new ClassCastException("Serialized object is not a Bean.");
            }
            return (B) ser;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
