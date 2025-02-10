package org.automation.exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PropertiesLoader {

    // compile time(checked) exception
    public static void loadFile() throws IOException {

        FileReader file = new FileReader("src/test/resources/data.txt");

        BufferedReader b = new BufferedReader(file);

        file.close();
    }

}
