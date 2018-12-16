package kg.demo.pizza.configuration;

import kg.demo.pizza.modells.Pizza;
import kg.demo.pizza.modells.Role;
import kg.demo.pizza.modells.User;
import kg.demo.pizza.repository.PizzasRepository;
import kg.demo.pizza.repository.RoleRepository;
import kg.demo.pizza.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLien implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CommandLien.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PizzasRepository pizzasRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void run(String... args) {
        log.info("Create users");
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");
        Role roleclient = new Role();
        roleclient.setName("CLIENT");
        roleRepository.save(role);
        roleRepository.save(roleclient);

        User user = new User();
        Set<Role> rl = new HashSet<Role>();
        rl.add(role);
        user.setName("Admin");
        user.setUsername("admin");
        user.setRoles(rl);
        user.setActive(1);
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        userRepository.save(user);


        Pizza pizza = new Pizza();
        pizza.setName("4 Сезона");
        pizza.setPrice(380f);
        pizza.setImageUrl("/pictures/4сезона.jpg");
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Гавайская");
        pizza.setPrice(400f);
        pizza.setImageUrl("/pictures/гавайская.jpg");
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Итальянская");
        pizza.setPrice(300f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Маргарита");
        pizza.setPrice(290f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Мексиканская");
        pizza.setPrice(350f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Морская");
        pizza.setPrice(370f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Мясная");
        pizza.setPrice(400f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Пепперони");
        pizza.setPrice(350f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Сальса");
        pizza.setPrice(380f);
        pizzasRepository.save(pizza);

        pizza = new Pizza();
        pizza.setName("Сырная");
        pizza.setPrice(250f);
        pizzasRepository.save(pizza);

    }

}