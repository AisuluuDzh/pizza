package kg.demo.pizza.modells;

import javax.persistence.*;

@Entity
public class OrderedPizza {
    @Id
    @GeneratedValue
    private  long id;
    private int count = 1;

    @OneToOne
    @JoinColumn(name="pizza_id")
    private Pizza pizza;

    @ManyToOne
    private User user;

    private boolean solt;

    private String date;

    public OrderedPizza(){

    }

    public OrderedPizza(User user, String date, Pizza pizza) {
        this.user = user;
        this.pizza = pizza;
        this.date = date;
    }


    public boolean isSolt() {
        return solt;
    }

    public void setSolt(boolean solt) {
        this.solt = solt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return pizza.getPrice() * count;
    }
}
