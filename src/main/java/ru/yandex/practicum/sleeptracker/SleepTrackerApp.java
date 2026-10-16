package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;


public class SleepTrackerApp {

    private static final List<SleepAnalysisFunction> FUNCTIONS = List.of(
            new TotalCountOfSleepSessions(),
            new MinimumOfSleepingTime(),
            new MaximumOfSleepingTime(),
            new AverageTimeOfSleeping(),
            new BadSleepSessions(),
            new NightsWithoutSleeping(),
            new ClassificationOfUser()
    );

    private static void SleepAnalysis(List<SleepingSession> sessions) {
        FUNCTIONS.forEach(function -> {
            SleepAnalysisResult result = function.apply(sessions);
            System.out.println(result);
        });
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.err.println("Укажите путь к файлу");
            return;
        }

        List<SleepingSession> sessions = new LinkedList<>();

        try {
            Files.readAllLines(Path.of(args[0]))
                    .stream()
                    .filter(line -> !line.isBlank())
                    .map(SleepingSession::parse)
                    .forEach(sessions::add);
        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл" + e.getMessage());
            return;
        }

        SleepAnalysis(sessions);
    }
}