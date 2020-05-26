package ru.shilov.cc.currencyconverter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String sourceCode;

    private String destinationCode;

    private Double amount;

}
