package com.safety.safetynet.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;

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
    ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    try {
      dataObject = objectMapper.readValue(
          new File("src/main/resources/data.json"),
          DataObject.class);

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
  public void write(final DataObject dataObject)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .enable(DeserializationFeature
            .ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        .configure(DeserializationFeature
            .USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
    try {
      objectMapper.writeValue(new File(
          "src/main/resources/data.json"), dataObject);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
