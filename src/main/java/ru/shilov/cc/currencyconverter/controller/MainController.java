package ru.shilov.cc.currencyconverter.controller;


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

    @GetMapping("/convert")
    public Result main(@RequestParam("amount") Double amount,
                       @RequestParam("fromCode") String fromCode,
                       @RequestParam("toCode") String toCode) {
        return convertationService.convert(fromCode, toCode, amount);
    }

}
