public abstract class TesteInt {

    public static final SerializingManagable getInitSerializingManager(){

        System.out.println("Início do Teste:!!!! ^^");
        return new SerializingManager();

    }
}
