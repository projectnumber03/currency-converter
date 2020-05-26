package ru.shilov.cc.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shilov.cc.currencyconverter.entity.Course;
import ru.shilov.cc.currencyconverter.entity.Currency;
import ru.shilov.cc.currencyconverter.entity.Result;

import java.util.Objects;

@Service
public class ConvertationService {

    public Result convert(final String fromCode, final String toCode, final Double amount) {
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
