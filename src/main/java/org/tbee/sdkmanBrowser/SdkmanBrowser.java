package org.tbee.sdkmanBrowser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tbee.sway.SFrame;
import org.tbee.sway.STree;
import picocli.CommandLine;

import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

public class SdkmanBrowser implements Callable<Void>, Runnable {

    static public void main(String[] args) throws Exception {
//        int exitCode = new CommandLine(new SdkmanBrowser()).execute(args);
//        if (exitCode != 0) {
//            System.exit(exitCode); // Needed so the tests get a result
//        }
        new SdkmanBrowser().call();
    }

    @CommandLine.Parameters(index = "0", description = "The SDKMAN JSON file as returned by https://api.foojay.io/disco/v3.0/packages", paramLabel = "json-file")
    private String json = null;

    // This is the PicoCLI entry point
    @Override
    public Void call() throws Exception {
        SwingUtilities.invokeAndWait(new SdkmanBrowser());
        return null;
    }

    // This is the EDT entry point
    public void run() {

        // Fetch data https://www.baeldung.com/jackson-jsonformat
        // TBEERNOT cache

        // Parse data
        ObjectMapper mapper = new ObjectMapper();
        try {
            SdkmanResult sdkmanResult = mapper.readValue(new File("./src/test/resources/packages.json"), SdkmanResult.class);
            System.out.println("!!!!" + sdkmanResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        SFrame.of(STree.of())
                .exitOnClose()
                .visible(true);
    }
}
