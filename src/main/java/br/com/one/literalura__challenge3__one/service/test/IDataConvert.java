package br.com.one.literalura__challenge3__one.service.test;

public interface IDataConvert {
    <T> T getData(String json, Class<T> classe);
}
