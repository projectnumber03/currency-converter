package ru.shilov.cc.currencyconverter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shilov.cc.currencyconverter.entity.ValuteDetail;
import ru.shilov.cc.currencyconverter.repo.ValuteDetailRepo;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public final class ValuteDetailService {

    private final ValuteDetailRepo valuteDetailRepo;

    public void save(final ValuteDetail valute) {
        valuteDetailRepo.save(valute);
    }

    public void saveAll(final Collection<ValuteDetail> valuteDetails) {
        valuteDetailRepo.saveAll(valuteDetails);
    }

    public ValuteDetail findByCharCode(final String charCode) {
        return valuteDetailRepo.findByCharCode(charCode);
    }

    public List<ValuteDetail> findAll() {
        return valuteDetailRepo.findAll();
    }

}
