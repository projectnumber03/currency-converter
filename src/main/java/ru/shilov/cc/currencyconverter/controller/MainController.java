package ru.shilov.cc.currencyconverter.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shilov.cc.currencyconverter.entity.Result;
import ru.shilov.cc.currencyconverter.service.ConvertationService;

@RestController
@RequiredArgsConstructor
public class MainController {

    @NonNull
    private final ConvertationService convertationService;

    @GetMapping("/convert")
    public Result main(/*@RequestParam("amount") final Double amount,
                       @RequestParam("fromCode") final String fromCode,
                       @RequestParam("toCode") final String toCode*/) {
        return convertationService.convert(convertationService.new Options("USD", "EUR", 10.0));
    }

}
