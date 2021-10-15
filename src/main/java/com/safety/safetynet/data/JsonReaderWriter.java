package com.safety.safetynet.data;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonReaderWriter {

    public DataObject read() {
        DataObject dataObject = new DataObject();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        objectMapper.setDateFormat(df);
        try {
            dataObject = objectMapper.readValue(new File("src/main/resources/data.json"), DataObject.class);

        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataObject;
    }

    public static void write(DataObject dataObject) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        objectMapper.setDateFormat(df);

        try {
            objectMapper.writeValue(new File("src/main/resources/data.json"), dataObject);
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
