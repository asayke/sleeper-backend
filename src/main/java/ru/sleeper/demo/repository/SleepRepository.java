package ru.sleeper.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sleeper.demo.model.Sleep;
import ru.sleeper.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
    Optional<Sleep> findById(Long id);

    List<Sleep> findAllByUser(User user);

    Optional<Sleep> findTopByUserOrderByIdDesc(User user);
}