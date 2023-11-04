package aalt.core.constants;

import java.util.ArrayList;
import java.util.Arrays;

public final class Capabilities {
    private Capabilities(){}
    public static ArrayList<String> getCapabilities(){
        return new ArrayList<>(
                Arrays.asList(
                        "--disable-extensions",
                        "--disable-gpu",
                        "--no-sandbox",
                        //"--headless",
                        "--disable-dev-shm-usage",
                        "--window-size=1920,1040"
                )
        );
    }
}
