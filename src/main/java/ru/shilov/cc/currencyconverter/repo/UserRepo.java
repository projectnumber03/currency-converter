package ru.shilov.cc.currencyconverter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shilov.cc.currencyconverter.entity.User;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    User findUserByLogin(String login);
}
