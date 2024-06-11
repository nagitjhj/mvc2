package com.hi.mvc2basic.typeconverter;

import com.hi.mvc2basic.typeconverter.converter.IntegerToStringConverter;
import com.hi.mvc2basic.typeconverter.converter.IpPortToStringConverter;
import com.hi.mvc2basic.typeconverter.converter.StringToIntegerConverter;
import com.hi.mvc2basic.typeconverter.converter.StringToIpPortConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {
    @Test
    void 문자에서숫자로() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void 숫자에서문자로() {
        IntegerToStringConverter c = new IntegerToStringConverter();
        String result = c.convert(10);
        assertThat(result).isEqualTo("10");
    }

    @Test
    void 문자에서아이디포트() {
        StringToIpPortConverter co = new StringToIpPortConverter();
        IpPort ipPort = co.convert("127.0.0.1:8080");
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    void 아이디포트에서문자로() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String ip = converter.convert(new IpPort("127.0.0.1", 8080));
        assertThat(ip).isEqualTo("127.0.0.1:8080");
    }
}
