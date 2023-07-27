package org.tbee.sdkmanBrowser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tbee.sway.SFrame;
import org.tbee.sway.STree;
import picocli.CommandLine;

import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
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
        // https://api.sdkman.io/2/candidates/all
        // https://api.sdkman.io/2/candidates/maven/win/versions/all
        //
        // Platforms: linuxx64, Darwin
        //
        // https://github.com/sdkman/sdkman-cli/blob/master/src/main/bash/sdkman-list.sh
        // https://github.com/sdkman/sdkman-candidates/blob/master/conf/routes
        // https://api.sdkman.io/2/candidates/default/maven
        // https://api.sdkman.io/2/candidates/all
        // https://api.sdkman.io/2/candidates/list
        // https://api.sdkman.io/2/candidates/maven/win/versions/list
        // https://api.sdkman.io/2/candidates/jvm/win/versions/list

        try {
            URL candidatesUrl = new URL("https://api.sdkman.io/2/candidates/all");
            String candidatesString = read(candidatesUrl.openStream());
            System.out.println(candidatesString);

            String[] candidates = candidatesString.split(",");
            for (String candidate : candidates) {

                URL versionsUrl = new URL("https://api.sdkman.io/2/candidates/" + candidate + "/win/versions/all");
                String versionsString = read(versionsUrl.openStream());
                System.out.println(candidate + " " + versionsUrl + ": " + versionsString);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        // Parse data
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        try {
//            SdkmanResult sdkmanResult = mapper.readValue(new File("./src/test/resources/packages.json"), SdkmanResult.class);
//            System.out.println("!!!!" + sdkmanResult);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        SFrame.of(STree.of())
//                .exitOnClose()
//                .visible(true);
    }

    private String read(InputStream inputStream) {
        StringBuffer content = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
