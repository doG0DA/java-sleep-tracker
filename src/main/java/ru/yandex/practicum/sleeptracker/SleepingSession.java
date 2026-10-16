package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");




    private LocalDate dayOfStart;
    private LocalTime timeOfStart;
    private LocalDate dayOfEnd;
    private LocalTime timeOfEnd;
    private QualityOfSleep quality;

    public SleepingSession(String dayOfStart, String timeOfStart, String dayOfEnd, String timeOfEnd, QualityOfSleep quality) {
        this.dayOfStart = LocalDate.parse(dayOfStart, DATE_FORMATTER);
        this.timeOfStart = LocalTime.parse(timeOfStart, TIME_FORMATTER);
        this.dayOfEnd = LocalDate.parse(dayOfEnd, DATE_FORMATTER);
        this.timeOfEnd = LocalTime.parse(timeOfEnd, TIME_FORMATTER);
        this.quality = quality;

    }

    public static SleepingSession parse(String shedule) {

        StringBuilder line = new StringBuilder(shedule);

        int indexOfSpace = line.indexOf(" ");
        String dayOfStart = line.substring(0,indexOfSpace);

        line.delete(0, indexOfSpace + 1);

        int indexOfPoint = line.indexOf(";");
        String timeOfStart = line.substring(0,indexOfPoint);

        line.delete(0,indexOfPoint + 1);

        indexOfSpace = line.indexOf(" ");
        String dayOfEnd = line.substring(0, indexOfSpace);

        line.delete(0,indexOfSpace + 1);

        indexOfPoint = line.indexOf(";");
        String timeOfEnd = line.substring(0,indexOfPoint);

        line.delete(0,indexOfPoint + 1);

        QualityOfSleep quality;

        try {
            quality = QualityOfSleep.valueOf(line.toString());
        } catch (IllegalArgumentException e) {
            quality = QualityOfSleep.ERROR;
        }

        return new SleepingSession(dayOfStart, timeOfStart, dayOfEnd, timeOfEnd, quality);
    }

    public LocalDate getDayOfStart() {
        return dayOfStart;
    }

    public void setDayOfStart(LocalDate dayOfStart) {
        this.dayOfStart = dayOfStart;
    }

    public LocalTime getTimeOfStart() {
        return timeOfStart;
    }

    public void setTimeOfStart(LocalTime timeOfStart) {
        this.timeOfStart = timeOfStart;
    }

    public LocalDate getDayOfEnd() {
        return dayOfEnd;
    }

    public void setDayOfEnd(LocalDate dayOfEnd) {
        this.dayOfEnd = dayOfEnd;
    }

    public LocalTime getTimeOfEnd() {
        return timeOfEnd;
    }

    public void setTimeOfEnd(LocalTime timeOfEnd) {
        this.timeOfEnd = timeOfEnd;
    }

    public QualityOfSleep getQuality() {
        return quality;
    }

    public void setQuality(QualityOfSleep quality) {
        this.quality = quality;
    }
}
