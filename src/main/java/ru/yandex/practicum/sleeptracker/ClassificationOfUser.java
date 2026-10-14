package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


public class ClassificationOfUser implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long allNights = sessions.stream()
                .map(session -> {
                    if (session.getTimeOfStart().isAfter(session.getTimeOfEnd())) {
                        return session.getDayOfEnd();
                    }
                    if (session.getTimeOfStart().getHour() < 6){
                        return  session.getDayOfStart();
                    }
                    return null;


                })
                .filter(Objects::nonNull)
                .distinct()
                .count();


        long owl = sessions.stream()
                .filter(session -> isNightSleep(session))
                .filter(session -> isOwl(session))
                .count();

        long lark = sessions.stream()
                .filter(session -> isNightSleep(session))
                .filter(session -> isLark(session))
                .count();


        long pigeons = allNights - lark - owl;

        Chronotype type;
        if (owl > lark && owl > pigeons) {
            type = Chronotype.OWL;
        } else if(lark > owl && lark > pigeons) {
            type = Chronotype.LARK;
        } else {
            type = Chronotype.PIGEON;
        }

        return new SleepAnalysisResult("Вы относитесь к классу: ", type);
    }

    public boolean isNightSleep(SleepingSession session) {

        boolean crossedMidnight = session.getTimeOfStart().isAfter(session.getTimeOfEnd());
        boolean afterMidnight = session.getTimeOfStart().getHour() < 6;

        return crossedMidnight || afterMidnight;

    }

    public boolean isOwl(SleepingSession session) {
        LocalTime start = session.getTimeOfStart();
        LocalTime end = session.getTimeOfEnd();

        boolean lateSleep = start.isAfter(LocalTime.of(23, 0)) || start.isBefore(LocalTime.of(6, 0));
        boolean lateEnd = end.isAfter(LocalTime.of(9, 0));

        return lateSleep && lateEnd;
    }

    public boolean isLark(SleepingSession session) {
        LocalTime start = session.getTimeOfStart();
        LocalTime end = session.getTimeOfEnd();

        boolean earlySleep = start.isBefore(LocalTime.of(22,0)) && start.isAfter(LocalTime.of(12,0));
        boolean earlyEnd = end.isBefore(LocalTime.of(7,0));

        return earlySleep && earlyEnd;


    }

}
