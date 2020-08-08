package ru.shilov.cc.currencyconverter;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;

import java.text.SimpleDateFormat;
import java.util.*;

public class BuilderTest {
    @Test
    public void test1() {
        ValuteDetail valuteDetail = ValuteDetail.builder().charCode("RUB").name("Рубль").numCode("123").id(UUID.randomUUID()).build();
        System.out.println(valuteDetail.getId());
    }

    @Test
    @SneakyThrows
    public void test2() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<Date> dates = Arrays.asList(sdf.parse("02.11.2015"), sdf.parse("03.11.2015"), sdf.parse("04.11.2015"));
        System.out.println(sdf.format(dates.stream().max(Date::compareTo).get()));
    }
}
