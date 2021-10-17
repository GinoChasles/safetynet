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

@Component
public class JsonReaderWriter {

    public DataObject read() {
        DataObject dataObject = new DataObject();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        ;
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

    public void write(DataObject dataObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        objectMapper.setDateFormat(df);

        try {
            objectMapper.writeValue(new File("src/main/resources/data.json"), dataObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
