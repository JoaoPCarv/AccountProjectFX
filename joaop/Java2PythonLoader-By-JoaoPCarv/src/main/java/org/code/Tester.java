package main.java;

import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {

    private static Tester instance;
    public static final String author = "JoaoPCarv";

    public static Tester getInstance() {
        if(instance == null) instance = new Tester();
        return instance;
    }

    private Tester() {}

    public void pyTest(String... args) throws IOException, InterruptedException {
        System.out.println("Teste Python");

    ProcessBuilder pyPB = new ProcessBuilder(args);
        Process pyProc = pyPB.start();
        InputStream pyInput = pyProc.getInputStream();
        System.out.println("Is it empty?... "
                + Boolean.valueOf(pyInput == null).toString().toUpperCase() + ".");

        Thread thread = new Thread(this.threadInvokerWithInputStream(pyInput, pyProc));
        thread.start();
    }

    private Runnable threadInvokerWithInputStream(InputStream is, Process proc){

        return () -> {
            try(BufferedReader myReader = new BufferedReader(new InputStreamReader(is))){
                String line;

                while((line = myReader.readLine()) != null)
                    System.out.println(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                assertEquals(proc.exitValue(), 0);
                proc.destroy();
            }
        };
    }
}
