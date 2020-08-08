package ru.shilov.cc.currencyconverter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;

import java.util.UUID;

public interface ValuteDetailRepo extends JpaRepository<ValuteDetail, UUID> {
    ValuteDetail findByCharCode(final String charCode);
}
