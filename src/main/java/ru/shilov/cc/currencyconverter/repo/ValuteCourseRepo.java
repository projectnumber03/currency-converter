package ru.shilov.cc.currencyconverter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shilov.cc.currencyconverter.entity.ValuteCourse;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;

import java.util.List;
import java.util.UUID;

public interface ValuteCourseRepo extends JpaRepository<ValuteCourse, UUID> {
    List<ValuteCourse> findByValuteDetail(final ValuteDetail valuteDetail);
}
