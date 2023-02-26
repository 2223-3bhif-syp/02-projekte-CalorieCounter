package at.htl.caloriecounter.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Goal {
    private Long id;
    private double weight;
    private LocalDateTime deadline;

    public Goal() {
    }

    public Goal(double weight, LocalDateTime deadline) {
        this.weight = weight;
        this.deadline = deadline;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "weight=" + weight +
                ", deadline=" + deadline +
                '}';
    }
}
