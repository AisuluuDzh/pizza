package kg.demo.pizza.controllers;

import kg.demo.pizza.modells.Role;
import kg.demo.pizza.modells.User;
import kg.demo.pizza.repository.PizzasRepository;
import kg.demo.pizza.repository.RoleRepository;
import kg.demo.pizza.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	private PizzasRepository pizzasRepository;

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping("/register")
	public ModelAndView registrform(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user) {
		user.setActive(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("CLIENT"));
		user.setRoles(roles);
		userRepository.save(user);
    	return "redirect:/login";
	}


}

