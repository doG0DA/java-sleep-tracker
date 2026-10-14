package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class NightsWithoutSleeping implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Ночей без сна", 0);
        }

        SleepingSession start = sessions.get(0);
        SleepingSession end = sessions.get(sessions.size() - 1);

        LocalDate firstNight = start.getTimeOfStart().getHour() >=  12
                ? start.getDayOfStart().plusDays(1)
                : start.getDayOfStart();

        LocalDate lastNight = end.getDayOfEnd();

        int totalNights = (int) ChronoUnit.DAYS.between(firstNight, lastNight) + 1;

        long nightWithSleeping = sessions.stream()
                .map(session -> {

                    if (session.getTimeOfStart().isAfter(session.getTimeOfEnd())) {
                        return session.getDayOfEnd();
                    }

                    if (session.getTimeOfStart().getHour() < 6) {
                        return session.getDayOfStart();
                    }

                    return null;

                })
                .filter(Objects::nonNull)
                .distinct()
                .count();

        return new SleepAnalysisResult("Всего было ночей без сна",  totalNights - (int) nightWithSleeping);



    }
}
