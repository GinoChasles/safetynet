package com.safety.safetynet.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The type Json reader writer.
 */
@Component
public class JsonReaderWriter {

    /**
     * Read data object.
     *
     * @return the data object
     */
    public DataObject read() {
        DataObject dataObject = new DataObject();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        ;
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        objectMapper.setDateFormat(df);
        try {
            dataObject = objectMapper.readValue(new File("src/main/resources/data.json"), DataObject.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataObject;
    }

    /**
     * Write.
     *
     * @param dataObject the data object
     * @throws JsonProcessingException the json processing exception
     */
    public void write(DataObject dataObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        objectMapper.setDateFormat(df);

        try {
            objectMapper.writeValue(new File("src/main/resources/data.json"), dataObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
