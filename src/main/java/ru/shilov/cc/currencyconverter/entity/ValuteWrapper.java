package ru.shilov.cc.currencyconverter.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement
public final class ValuteWrapper {

    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<ValuteDto> valutes = new ArrayList<>();

}
