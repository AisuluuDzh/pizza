package kg.demo.pizza.controllers;

import kg.demo.pizza.modells.OrderedPizza;
import kg.demo.pizza.modells.Pizza;
import kg.demo.pizza.modells.User;
import kg.demo.pizza.repository.OrderedPizzasRepository;
import kg.demo.pizza.repository.PizzasRepository;
import kg.demo.pizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ForClients {
    @Autowired
    private PizzasRepository pizzasRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderedPizzasRepository orderedPizzasRepository;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView view) {
        view.addObject("pizzas", pizzasRepository.findAll());
        view.setViewName("ForClients/index");
        return view;
    }

    @RequestMapping("/order/{id}")
    public String order(@PathVariable("id") Long id, @RequestParam("count") int count, ModelAndView view, Principal principal){
        Pizza pizza = pizzasRepository.getOne(id);
        User user = userRepository.findByUsername(principal.getName());
        OrderedPizza orderedPizza = new OrderedPizza(user, new Date().toString(), pizza);
        orderedPizza.setCount(count);
        orderedPizzasRepository.save(orderedPizza);
        user.getOrders().add(orderedPizza);
        userRepository.save(user);
        return "redirect:/order/all";
    }

    @RequestMapping("/order/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelAndView view, Principal principal){
        OrderedPizza orderedPizza = orderedPizzasRepository.getOne(id);
        orderedPizzasRepository.delete(orderedPizza);
        return "redirect:/order/all";
    }




    @RequestMapping("/order/all")
    public ModelAndView orders(ModelAndView view, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        view.setViewName("ForClients/orders");
        view.addObject("orders", user.uncompletedOrders());
        Double totalPrice = 0.0;
        for (OrderedPizza orderedPizza : user.uncompletedOrders()) {
            totalPrice += orderedPizza.getPizza().getPrice();
        }
        view.addObject("totalPrice", totalPrice);

        return view;
    }


    @RequestMapping("/order/checkout")
    public ModelAndView checkout(ModelAndView modelAndView, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("ForClients/checkout");
        Double totalPrice = 0.0;
        for (OrderedPizza orderedPizza : user.uncompletedOrders()) {
            totalPrice += orderedPizza.getPizza().getPrice();
            orderedPizza.setSolt(true);
            orderedPizzasRepository.save(orderedPizza);
        }
        modelAndView.addObject("totalPrice", totalPrice);
        return modelAndView;
    }



}
