package ru.shilov.cc.currencyconverter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String sourceCode;

    private String destinationCode;

    private Double amount;

    private Date date;

}
