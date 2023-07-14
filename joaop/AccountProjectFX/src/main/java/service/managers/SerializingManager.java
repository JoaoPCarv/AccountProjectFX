package service.managers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class SerializingManager {

    @Deprecated(since = "1.0")
    private SerializingManager() { /* This class should not be instantiated. */ }

    public static void writeObject(Serializable ser, ObjectOutputStream out) throws IOException {

        try(out){
            out.writeObject(ser);
            out.flush();
        }
    }

    public static Serializable readObject(ObjectInputStream in) throws NullPointerException,
            IOException, ClassNotFoundException {

        try (in) {
            Object o = in.readObject();

            if(o == null) throw new NullPointerException("Serial-Reading did not succeed.");

            if (!(o instanceof Serializable))
                throw new ClassCastException("Object read from File is not Serializable.");

            return (Serializable) o;
        }
    }
}
