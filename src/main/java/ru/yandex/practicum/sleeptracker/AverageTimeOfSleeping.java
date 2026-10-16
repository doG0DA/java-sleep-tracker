package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AverageTimeOfSleeping implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        double totalTime = sessions.stream()
                .mapToLong(session -> {
                    LocalDateTime start = LocalDateTime.of(session.getDayOfStart(), session.getTimeOfStart());
                    LocalDateTime end = LocalDateTime.of(session.getDayOfEnd(), session.getTimeOfEnd());
                    return (int) Duration.between(start, end).toMinutes();
                })
                .average()
                .orElse(0);


        return new SleepAnalysisResult("Среднее время сессии в минутах составило", (int) totalTime);
    }
}
