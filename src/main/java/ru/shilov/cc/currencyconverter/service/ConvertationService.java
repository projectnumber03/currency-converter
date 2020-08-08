package ru.shilov.cc.currencyconverter.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shilov.cc.currencyconverter.config.CourseConfig;
import ru.shilov.cc.currencyconverter.entity.Result;
import ru.shilov.cc.currencyconverter.entity.ValuteCourse;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ConvertationService {

    private final ValuteCourseService valuteCourseService;

    private final ValuteDetailService valuteDetailService;

    private final CourseConfig courseConfig;

    public Result convert(final ConvertationService.Options options) {
        return getResult(options);
    }

    private Result getResult(final ConvertationService.Options options) {
        final ValuteDetail fromValuteDetail = valuteDetailService.findByCharCode(options.getFromCode());
        final ValuteCourse fromValuteCourse = valuteCourseService.findByValuteDetail(fromValuteDetail).stream().max(Comparator.comparing(ValuteCourse::getDate)).orElseThrow(RuntimeException::new);
        final ValuteDetail toValuteDetail = valuteDetailService.findByCharCode(options.getToCode());
        final ValuteCourse toValuteCourse = valuteCourseService.findByValuteDetail(toValuteDetail).stream().max(Comparator.comparing(ValuteCourse::getDate)).orElseThrow(RuntimeException::new);
        if (!isActualCourse(fromValuteCourse) || !isActualCourse(toValuteCourse)) {
            valuteCourseService.saveAll(courseConfig.valute().values());
            return getResult(options);
        }
        return new Result(
                options.getFromCode(),
                options.getToCode(),
                options.getAmount() * fromValuteCourse.getValue() / toValuteCourse.getValue(),
                new Date()
        );
    }

    private boolean isActualCourse(ValuteCourse valuteCourse) {
        return !valuteCourse.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now());
    }

    @Getter
    @AllArgsConstructor
    public class Options {
        final String fromCode;
        final String toCode;
        final Double amount;
    }

}
