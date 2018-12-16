package kg.demo.pizza.repository;

import kg.demo.pizza.modells.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzasRepository extends JpaRepository<Pizza, Long> {
}
