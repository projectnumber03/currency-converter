package ru.shilov.cc.currencyconverter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shilov.cc.currencyconverter.entity.Result;
import ru.shilov.cc.currencyconverter.repo.ResultRepo;

import java.util.List;

@Service
@AllArgsConstructor
public final class ResultService {

    private final ResultRepo resultRepo;

    public void save(final Result result) {
        resultRepo.save(result);
    }

    public List<Result> findAll() {
        return resultRepo.findAll();
    }

}
