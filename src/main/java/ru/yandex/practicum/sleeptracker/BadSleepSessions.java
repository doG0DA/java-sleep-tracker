package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadSleepSessions implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        int count = (int) sessions.stream()
                .filter(s -> s.getQuality() == QualityOfSleep.BAD)
                .count();

        return new SleepAnalysisResult("Плохих сессий сна было", count);

    }
}
