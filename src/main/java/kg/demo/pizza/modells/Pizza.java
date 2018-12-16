package kg.demo.pizza.modells;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Float price;

	private String imageUrl;

	@OneToOne
	private OrderedPizza orderedPizza;


	public Pizza() {
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public OrderedPizza getOrderedPizza() {
		return orderedPizza;
	}

	public void setOrderedPizza(OrderedPizza orderedPizza) {
		this.orderedPizza = orderedPizza;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Float getPrice() {
		return price;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
