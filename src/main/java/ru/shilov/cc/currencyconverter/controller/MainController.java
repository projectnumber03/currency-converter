package ru.shilov.cc.currencyconverter.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shilov.cc.currencyconverter.entity.Result;
import ru.shilov.cc.currencyconverter.service.ConvertationService;

@RestController
public class MainController {

    private final ConvertationService convertationService;

    public MainController(ConvertationService convertationService) {
        this.convertationService = convertationService;
    }

    @GetMapping(value = "/convert", produces= MediaType.APPLICATION_JSON_VALUE)
    public Result main(@RequestParam("amount") final Double amount,
                       @RequestParam("fromCode") final String fromCode,
                       @RequestParam("toCode") final String toCode) {
        return convertationService.convert(fromCode, toCode, amount);
    }

}
