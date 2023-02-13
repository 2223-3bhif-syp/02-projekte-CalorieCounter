package at.htl.caloriecounter.entity;

import java.util.Date;

public class Goal {
    private double weight;
    private Date deadline;

    public Goal(double weight, Date deadline) {
        this.weight = weight;
        this.deadline = deadline;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
