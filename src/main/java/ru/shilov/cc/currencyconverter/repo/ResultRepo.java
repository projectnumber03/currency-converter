package ru.shilov.cc.currencyconverter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shilov.cc.currencyconverter.entity.Result;

import java.util.UUID;

public interface ResultRepo extends JpaRepository<Result, UUID> {
}
