package at.htl.caloriecounter.entity;

import java.time.LocalDate;

public class Goal {
    private Long id;
    private double weight;
    private LocalDate deadline;

    public Goal() {
    }

    public Goal(Long id, double weight, LocalDate deadline) {
        this.id = id;
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
