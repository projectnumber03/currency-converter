package ru.shilov.cc.currencyconverter.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shilov.cc.currencyconverter.entity.Result;
import ru.shilov.cc.currencyconverter.service.ConvertationService;
import ru.shilov.cc.currencyconverter.service.ResultService;
import ru.shilov.cc.currencyconverter.service.ValuteDetailService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public final class MainController {

    private final ValuteDetailService valuteDetailService;

    private final ConvertationService convertationService;

    private final ResultService resultService;

    @GetMapping("/")
    public String index() {
        return "redirect:/converter";
    }

    @GetMapping("/converter")
    public String converter(final Map<String, Object> model) {
        final List<String> charCodes = valuteDetailService.findAll().stream()
                .map(vd -> String.format("%s (%s)", vd.getCharCode(), vd.getName()))
                .collect(Collectors.toList());
        model.put("charCodes", charCodes);
        return "main";
    }

    @PostMapping(value = "/converter", params = "history")
    public String showHistory() {
        return "redirect:/history";
    }

    @GetMapping("/history")
    public String history(final Map<String, Object> model) {
        model.put("results", resultService.findAll());
        return "history";
    }

    @PostMapping(value = "/history", params = "back")
    public String back() {
        return "redirect:/converter";
    }

    @ResponseBody
    @PostMapping("/convert")
    public String result(
            @RequestParam("amount") final Double amount,
            @RequestParam("fromCode") final String fromCode,
            @RequestParam("toCode") final String toCode
    ) {
        final Result result = convertationService.convert(new ConvertationService.Options(fromCode.substring(0, 3), toCode.substring(0, 3), amount));
        resultService.save(result);
        return new DecimalFormat("#0.00").format(result.getDestinationAmount());
    }

}
