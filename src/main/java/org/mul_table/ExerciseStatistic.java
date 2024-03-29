package org.mul_table;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

public class ExerciseStatistic {
    User user;
    LocalDateTime start_time;
    Duration total_duration;
    ArrayList<Question> questions;
    double speed_score;
    double accuracy_score;
    Exercise e_mode;

    public ExerciseStatistic(LocalDateTime start_time, User user, Exercise e_mode) {
        this.start_time = start_time;
        this.user = user;
        this.e_mode = e_mode;
        this.total_duration = Duration.ZERO;
        this.questions = new ArrayList<Question>();
    }

    public ArrayList<Question> get_questions() {
        return this.questions;
    }

    public Exercise get_e_mode() {
        return this.e_mode;
    }

    public LocalDateTime get_start_time() {
        return this.start_time;
    }

    public Duration get_total_duration() {
        return this.total_duration;
    }
    public void set_total_duration(Duration duration) {
        this.total_duration = duration;
    }

    public void extend_total_duration(Duration duration) {
        this.total_duration = this.total_duration.plus(duration);
    }

    public void calculate_scores() {
        int accurate_count = 0;
        for (Question q: this.questions)
            if (!q.is_false())
                ++accurate_count;
        this.accuracy_score = 100 * accurate_count / (double) this.questions.size();
        this.accuracy_score = Math.round(this.accuracy_score * 100) / 100.0;

        double average = 0;
        for (Question q: this.questions)
            average += 100.0 * (Math.pow(2, -q.get_answer_duration().toMillis() / 10000.0));
        average /= this.questions.size();
        this.speed_score = Math.round(average * 100) / 100.0;
    }

    public double get_accuracy_score() {
        return this.accuracy_score;
    }
    public void set_accuracy_score(double score) {
        this.accuracy_score = score;
    }

    public double get_speed_score() {
        return this.speed_score;
    }
    public void set_speed_score(double score) {
        this.speed_score = score;
    }

    public User get_user() {
        return this.user;
    }
}
