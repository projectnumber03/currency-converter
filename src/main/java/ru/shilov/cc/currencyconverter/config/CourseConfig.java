package ru.shilov.cc.currencyconverter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.shilov.cc.currencyconverter.entity.ValuteCourse;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;
import ru.shilov.cc.currencyconverter.entity.ValuteDto;
import ru.shilov.cc.currencyconverter.entity.ValuteWrapper;
import ru.shilov.cc.currencyconverter.service.ValuteCourseService;
import ru.shilov.cc.currencyconverter.service.ValuteDetailService;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class CourseConfig {

    @Value("${valute.server.address}")
    private String url;

    private final ValuteDetailService valuteDetailService;

    private final ValuteCourseService valuteCourseService;

    public ValuteWrapper valuteWrapper() {
        return new RestTemplate().getForObject(url, ValuteWrapper.class);
    }

    private ValuteDetail buildDetail(final ValuteDto dto) {
        final ValuteDetail valuteDetail = valuteDetailService.findByCharCode(dto.getCharCode());
        final UUID id = Objects.isNull(valuteDetail) ? UUID.randomUUID() : valuteDetail.getId();
        return ValuteDetail.builder()
                .id(id)
                .name(dto.getName())
                .charCode(dto.getCharCode())
                .numCode(dto.getNumCode())
                .build();
    }

    public ValuteCourse buildCourse(final ValuteDto dto) {
        final ValuteDetail valuteDetail = valuteDetailService.findByCharCode(dto.getCharCode());
        final List<ValuteCourse> valuteCourses = valuteCourseService.findByValuteDetail(valuteDetail);
        if (!valuteCourses.isEmpty() && valuteCourses.stream().anyMatch(valuteCourseService::isActualCourse)) {
            return valuteCourses.stream().filter(valuteCourseService::isActualCourse).findAny().orElseThrow(RuntimeException::new);
        }
        return ValuteCourse.builder()
                .id(UUID.randomUUID())
                .date(new Date())
                .nominal(dto.getNominal())
                .value(dto.getValue())
                .build();
    }

    public ValuteDetail roubleDetail() {
        final ValuteDetail valuteDetail = valuteDetailService.findByCharCode("RUB");
        final UUID id = Objects.isNull(valuteDetail) ? UUID.randomUUID() : valuteDetail.getId();
        return ValuteDetail.builder()
                .id(id)
                .name("Российский рубль")
                .charCode("RUB")
                .numCode("643")
                .build();
    }

    public ValuteCourse roubleCourse() {
        final List<ValuteCourse> valuteCourses = valuteCourseService.findByValuteDetail(roubleDetail());
        if (!valuteCourses.isEmpty() && valuteCourses.stream().anyMatch(valuteCourseService::isActualCourse)) {
            return valuteCourses.stream().filter(valuteCourseService::isActualCourse).findAny().orElseThrow(RuntimeException::new);
        }
        return ValuteCourse.builder()
                .id(UUID.randomUUID())
                .valuteDetail(roubleDetail())
                .date(new Date())
                .nominal(1)
                .value(1.0)
                .build();
    }

    public Map<ValuteDetail, ValuteCourse> valute() {
        return valuteWrapper().getValutes().stream()
                .collect(Collectors.toMap(this::buildDetail, this::buildCourse))
                .entrySet().stream().peek(entry -> entry.getValue().setValuteDetail(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Bean
    public void init() {
        final Map<ValuteDetail, ValuteCourse> valute = valute();
        valuteDetailService.saveAll(valute.keySet());
        valuteDetailService.save(roubleDetail());
        valuteCourseService.saveAll(valute.values());
        valuteCourseService.save(roubleCourse());
    }

}
