package ru.shilov.cc.currencyconverter.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shilov.cc.currencyconverter.util.ValueDeserializer;

@Getter
@Setter
@NoArgsConstructor
public class ValuteDto {

    @JacksonXmlProperty(localName = "NumCode")
    private String numCode;

    @JacksonXmlProperty(localName = "CharCode")
    private String charCode;

    @JacksonXmlProperty(localName = "Nominal")
    private Integer nominal;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JsonDeserialize(using = ValueDeserializer.class)
    @JacksonXmlProperty(localName = "Value")
    private Double value;

}
