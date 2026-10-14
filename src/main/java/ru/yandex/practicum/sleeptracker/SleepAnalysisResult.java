package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepAnalysisResult {

    private final String description;
    private final int sessions;
    private final Chronotype chronotype;

    public SleepAnalysisResult(String description, int sessions) {
        this.description = description;
        this.sessions = sessions;
        this.chronotype = null;
    }

    public SleepAnalysisResult(String description, Chronotype type) {
        this.description = description;
        this.sessions = 0;
        this.chronotype = type;
    }


    public String getDescription() {
        return description;
    }

    public int getSessions() {
        return sessions;
    }

    @Override
    public String toString() {
        if (chronotype != null) {
            return description + chronotype;
        }
        return description + ": " + sessions;
    }
}
