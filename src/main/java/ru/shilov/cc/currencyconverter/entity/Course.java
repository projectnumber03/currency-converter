package ru.shilov.cc.currencyconverter.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Course {

    public final List<Currency> currencies = new ArrayList<>();

    @JsonAnySetter
    public void set(final String code, final Currency currency) {
        this.currencies.add(currency);
    }

}
