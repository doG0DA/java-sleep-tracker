package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MinimumOfSleepingTime implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int totalMinutes = (int) sessions.stream()
                .mapToInt(session -> {
                    LocalDateTime start = LocalDateTime.of(session.getDayOfStart(), session.getTimeOfStart());
                    LocalDateTime end = LocalDateTime.of(session.getDayOfEnd(), session.getTimeOfEnd());
                    return (int) Duration.between(start, end).toMinutes();
                })
                .min()
                .orElse(0);

        return new SleepAnalysisResult("Минимальная продолжительность сессии в минутах", totalMinutes);


    }
}
