package aop;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public interface TestUtils {

    String LINE_SEPARATOR =
            System.getProperty("line.separator");

    @SneakyThrows
    static String fromSystemOut(Runnable runnable) {

        PrintStream realOut = System.out;

        val out = new ByteArrayOutputStream();
        @Cleanup val printStream = new PrintStream(out);

        System.setOut(printStream);
        runnable.run();
        System.setOut(realOut);

        return new String(out.toByteArray());
    }
}
