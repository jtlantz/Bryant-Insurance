package org.bryantinsurance.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class AjaxUtils {

    public static String convertToString(Object objectValue){
        StringWriter stringWriter = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(stringWriter, objectValue);
            return stringWriter.toString();
        } catch (IOException e) {
            return "[bad/object]";
        }
    }
}
