package ru.shilov.cc.currencyconverter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shilov.cc.currencyconverter.entity.ValuteCourse;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;
import ru.shilov.cc.currencyconverter.repo.ValuteCourseRepo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public final class ValuteCourseService {

    private final ValuteCourseRepo valuteCourseRepo;

    public void save(final ValuteCourse valuteCourse) {
        valuteCourseRepo.save(valuteCourse);
    }

    public void saveAll(final Collection<ValuteCourse> valuteCourses) {
        valuteCourseRepo.saveAll(valuteCourses);
    }

    public List<ValuteCourse> findByValuteDetail(final ValuteDetail valuteDetail) {
        return valuteCourseRepo.findByValuteDetail(valuteDetail);
    }

    public boolean isActualCourse(final ValuteCourse valuteCourse) {
        return !valuteCourse.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now());
    }

}
