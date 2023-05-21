package service.serializingService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class SerializingManager {

    @Deprecated
    private SerializingManager() {}

    public static void writeObject(Serializable ser, ObjectOutputStream out) throws IOException {

        try(out){
            out.writeObject(ser);
            out.flush();
        }
    }

    public static Serializable readObject(ObjectInputStream in) throws NullPointerException, IOException {

        Serializable ser = null;

        try (in) {
            try {
                Object o = in.readObject();
                if (!(o instanceof Serializable))
                    throw new ClassCastException("Object read from File is not Serializable.");

                ser = (Serializable) o;

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if(ser == null) throw new NullPointerException("Serial-Reading did not succeed.");

        return ser;
    }
}
