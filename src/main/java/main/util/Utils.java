package main.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Sherif on 6/15/2018.
 */
public class Utils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static  <V> String convertToJson(V v){
        try {
            return mapper.writeValueAsString(v);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E convertValue(String s,Class<E> c){
        try {
            return mapper.readValue(s,c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
