package com.study.spring.filter.coder;

public interface Encoder {

    public String encode(String value);

    public byte[] encode(byte[] value);

}
