package ru.shilov.cc.currencyconverter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Course {

    private Currency usd;

    private Currency eur;

    private Currency rub;

    @NotNull
    public List<Currency> getCurrencies() {
        return Arrays.asList(usd, eur, rub);
    }

}
