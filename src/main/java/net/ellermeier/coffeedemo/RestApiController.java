package net.ellermeier.coffeedemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
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
    @GetMapping
    Iterable<Coffee> getCoffees() {
        return this.coffees;
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for (Coffee c: coffees) {
            if(c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Coffee addCoffee(@RequestBody Coffee c) {
        coffees.add(c);
        return c;

    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee putCoffee) {
        int coffeeIndex = -1;

        for (Coffee c: coffees) {
            if(c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, putCoffee);
            }
        }
        return coffeeIndex == -1 ?
                new ResponseEntity<>(addCoffee(putCoffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffees.get(coffeeIndex), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}

