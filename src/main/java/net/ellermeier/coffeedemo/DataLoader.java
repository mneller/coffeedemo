package net.ellermeier.coffeedemo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {
    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        this.coffeeRepository.saveAll(List.of(
                Coffee.of("Dark Coffee"),
                Coffee.of("Very Dark Coffee"),
                Coffee.of("Light Coffee")
        ));
    }
}
