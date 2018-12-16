package kg.demo.pizza.modells;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderedPizza> orders;

    @NotNull
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private int active;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<Role>();

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
        orders = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", UserName='" + username + '\'' +
                ", Password='" + Password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }

    public double moneySpent() {
        double total = 0;
        for (OrderedPizza pizza : getOrders()) {
            total += pizza.getPizza().getPrice()*pizza.getCount();
        }
        return total;
    }

    public List<OrderedPizza> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderedPizza> orders) {
        this.orders = orders;
    }

    public List<OrderedPizza> completedOrders() {
        List<OrderedPizza> orderedPizzas = new ArrayList<>();
        for (OrderedPizza pizza : getOrders()) {
            if (pizza.isSolt()) {
                orderedPizzas.add(pizza);
            }
        }
        return orderedPizzas;
    }

    public List<OrderedPizza> uncompletedOrders() {
        List<OrderedPizza> orderedPizzas = new ArrayList<>();
        for (OrderedPizza pizza : getOrders()) {
            if (!pizza.isSolt()) {
                orderedPizzas.add(pizza);
            }
        }
        return orderedPizzas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}