package net.ellermeier.coffeedemo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class RestApiController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiController() {
        coffees.addAll(List.of(
                Coffee.of("Dark Coffee"),
                Coffee.of("Very Dark Coffee"),
                Coffee.of("Light Coffee")
        ));
    }

    // @RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping("/coffees")
    Iterable<Coffee> getCoffees() {
        return this.coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for (Coffee c: coffees) {
            if(c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees")
    Coffee addCoffee(@RequestBody Coffee c) {
        coffees.add(c);
        return c;

    }

    @PutMapping("/coffees/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee putCoffee) {
        int coffeeIndex = -1;

        for (Coffee c: coffees) {
            if(c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, putCoffee);
            }
        }
        return coffeeIndex == -1 ? addCoffee(putCoffee) : coffees.get(coffeeIndex);
    }

    @DeleteMapping("/coffees/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}

