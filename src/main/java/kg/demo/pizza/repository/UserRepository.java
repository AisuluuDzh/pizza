package kg.demo.pizza.repository;

import kg.demo.pizza.modells.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    List<User> findByRoles_Name(String name);
}
