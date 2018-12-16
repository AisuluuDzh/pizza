package kg.demo.pizza.repository;

import kg.demo.pizza.modells.OrderedPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedPizzasRepository extends JpaRepository<OrderedPizza, Long>{

    List<OrderedPizza> findBySolt(boolean value);
}
