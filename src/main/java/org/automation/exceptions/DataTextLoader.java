package org.automation.exceptions;

public class DataTextLoader {

    //runtime(unchecked) exception
    static String data = "text";

    public static void productsLoader() {
        try {
            int sizeOfData = data.length();
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        } finally {
            System.out.println("finally block executed");
        }
    }
}
