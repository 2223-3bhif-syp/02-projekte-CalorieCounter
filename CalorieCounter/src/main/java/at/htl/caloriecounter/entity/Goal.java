package at.htl.caloriecounter.entity;

import java.time.LocalDate;

public class Goal {
    private double weight;
    private LocalDate deadline;

    public Goal() {
    }

    public Goal(double weight, LocalDate deadline) {
        this.weight = weight;
        this.deadline = deadline;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "weight=" + weight +
                ", deadline=" + deadline +
                '}';
    }
}
