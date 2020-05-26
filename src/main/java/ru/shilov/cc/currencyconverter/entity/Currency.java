package ru.shilov.cc.currencyconverter.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Currency {

    private String code;

    private String alphaCode;

    private Integer numericCode;

    private String name;

    private Double rate;

    private Date date;

    private Double inverseRate;

}
