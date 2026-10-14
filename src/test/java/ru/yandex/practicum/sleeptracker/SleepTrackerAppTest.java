package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SleepTrackerAppTest {

    @Test
    void totalCountOfSessions0() {

        TotalCountOfSleepSessions function = new TotalCountOfSleepSessions();

        SleepAnalysisResult result = function.apply(List.of());

        assertEquals(0, result.getSessions());

    }

    @Test
    void totalCountOfSessions1() {

        TotalCountOfSleepSessions function = new TotalCountOfSleepSessions();

        SleepingSession session = new SleepingSession("05.10.25", "00:10", "05.10.25", "06:20", QualityOfSleep.BAD);
        SleepAnalysisResult result = function.apply(List.of(session));

        assertEquals(1, result.getSessions());
    }

    @Test
    void timeOfTheSmallestSessionIs240Minutes() {

        MinimumOfSleepingTime function = new MinimumOfSleepingTime();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "06:20", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "04:10", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Минимальная продолжительность сессии в минутах: 240 минут!", result.toString());
    }

    @Test
    void timeOfTheSmallestSessionIs10Minutes() {

        MinimumOfSleepingTime function = new MinimumOfSleepingTime();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "06:20", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Минимальная продолжительность сессии в минутах: 10 минут!", result.toString());
    }

    @Test
    void timeOfTheLongestSessionIs240Minutes() {

        MaximumOfSleepingTime function = new MaximumOfSleepingTime();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "04:10", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Максимальная продолжительность сессии в минутах составила: 240 минут!", result.toString());
    }

    @Test
    void timeOfTheLongestSessionIs10Minutes() {

        MaximumOfSleepingTime function = new MaximumOfSleepingTime();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:05", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Максимальная продолжительность сессии составила: 10 минут!", result.toString());
    }

    @Test
    void averageTimeOfSleepSesionsIs20Minutes() {

        AverageTimeOfSleeping function = new AverageTimeOfSleeping();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:40", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Среднее время сессии в минутах составило: 20", result.toString());
    }

    @Test
    void averageTimeOfSleepSesionsIs15Minutes() {

        AverageTimeOfSleeping function = new AverageTimeOfSleeping();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:30", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Среднее время сессии в минутах составило: 15", result.toString());
    }


    @Test
    void countOfNightsWithBadMark2() {

        BadSleepSessions function = new BadSleepSessions();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.BAD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:40", QualityOfSleep.BAD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Плохих сессий сна было: 2", result.toString());
    }

    @Test
    void countOfNightsWithBadMark0() {

        BadSleepSessions function = new BadSleepSessions();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.GOOD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:40", QualityOfSleep.GOOD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Плохих сессий сна было: 0", result.toString());
    }

    @Test
    void countOfNightsWithoutSleeping0() {

        NightsWithoutSleeping function = new NightsWithoutSleeping();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.GOOD);
        SleepingSession session2 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:40", QualityOfSleep.GOOD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Всего было ночей без сна: 0", result.toString());
    }

    @Test
    void countOfNightsWithoutSleeping1() {

        NightsWithoutSleeping function = new NightsWithoutSleeping();

        SleepingSession session1 = new SleepingSession("05.10.25", "07:00", "05.10.25", "20:00", QualityOfSleep.GOOD);


        SleepAnalysisResult result = function.apply(List.of(session1));

        assertEquals("Всего было ночей без сна: 1", result.toString());
    }

    @Test
    void countOfNightsWithoutSleeping2() {

        NightsWithoutSleeping function = new NightsWithoutSleeping();

        SleepingSession session1 = new SleepingSession("06.10.25", "06:10", "06.10.25", "09:00", QualityOfSleep.GOOD);
        SleepingSession session2 = new SleepingSession("05.10.25", "07:00", "05.10.25", "21:00", QualityOfSleep.GOOD);

        SleepAnalysisResult result = function.apply(List.of(session2,session1));

        assertEquals("Всего было ночей без сна: 2", result.toString());
    }

    @Test
    void countOfEmptyListOfSleeping() {

        NightsWithoutSleeping function = new NightsWithoutSleeping();

        SleepAnalysisResult result = function.apply(List.of());

        assertEquals("Ночей без сна: 0", result.toString());
    }

    @Test
    void isPersonWithScheduleOwl() {

        ClassificationOfUser function = new ClassificationOfUser();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "10:00", QualityOfSleep.GOOD);
        SleepingSession session2 = new SleepingSession("06.10.25", "23:10", "07.10.25", "09:10", QualityOfSleep.GOOD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Вы относитесь к классу: Сова", result.toString());
    }

    @Test
    void isPersonWithSchedulePigeon() {

        ClassificationOfUser function = new ClassificationOfUser();

        SleepingSession session1 = new SleepingSession("05.10.25", "00:10", "05.10.25", "00:20", QualityOfSleep.GOOD);
        SleepingSession session2 = new SleepingSession("06.10.25", "23:10", "07.10.25", "00:20", QualityOfSleep.GOOD);

        SleepAnalysisResult result = function.apply(List.of(session1,session2));

        assertEquals("Вы относитесь к классу: Голубь", result.toString());
    }





}