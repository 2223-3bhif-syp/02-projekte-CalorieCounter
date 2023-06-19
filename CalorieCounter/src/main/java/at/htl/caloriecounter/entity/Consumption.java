package at.htl.caloriecounter.entity;

public class Consumption {
    private Long id;
    private User user;
    private Food food;
    private int amount;

    public Consumption() {}

    public Consumption(User user, Food food, int amount) {
        this.user = user;
        this.food = food;
        setAmount(amount);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if(amount < 0){
            throw new IllegalArgumentException("calories cannot be 0 or less");
        }

        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", amount, food);
    }
}
