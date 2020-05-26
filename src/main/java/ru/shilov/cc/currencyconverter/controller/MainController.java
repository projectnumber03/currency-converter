package ru.shilov.cc.currencyconverter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.shilov.cc.currencyconverter.entity.Course;
import ru.shilov.cc.currencyconverter.entity.Currency;
import ru.shilov.cc.currencyconverter.entity.Result;

import java.util.Objects;

@RestController
public class MainController {

    @GetMapping("/convert")
    public Result main(@RequestParam("amount") Integer amount,
                       @RequestParam("fromCode") String fromCode,
                       @RequestParam("toCode") String toCode) {
        final String formattedURL = String.format("http://www.floatrates.com/daily/%s.json", toCode.toLowerCase());
        final Course course = new RestTemplate().getForObject(formattedURL, Course.class);
        final Double rate;
        if (course == null) rate = 0.0;
        else rate = course.getCurrencies().stream()
                .filter(Objects::nonNull)
                .filter(currency -> fromCode.equals(currency.getCode()))
                .map(Currency::getInverseRate).findAny().orElse(0.0);
        return new Result(fromCode, toCode, amount * rate);
    }

}
