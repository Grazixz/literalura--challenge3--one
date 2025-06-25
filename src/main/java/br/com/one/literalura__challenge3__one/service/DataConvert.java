package br.com.one.literalura__challenge3__one.service;

import br.com.one.literalura__challenge3__one.service.test.IDataConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IDataConvert {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T getData(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
