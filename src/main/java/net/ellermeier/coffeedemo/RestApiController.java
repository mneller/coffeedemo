package net.ellermeier.coffeedemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class RestApiController {
    private final CoffeeRepository coffeeRepository;

    public RestApiController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

     @GetMapping
    Iterable<Coffee> getCoffees() {
        return this.coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return this.coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee addCoffee(@RequestBody Coffee c) {
        return coffeeRepository.save(c);
    }

    @PatchMapping
    ResponseEntity<Coffee> patchCoffee(@RequestBody Coffee patchCoffee) {
        return coffeeRepository.existsById(patchCoffee.getId())
                ? new ResponseEntity<>(coffeeRepository.save(patchCoffee), HttpStatus.OK)
                : new ResponseEntity<>(patchCoffee, HttpStatus.NOT_FOUND);
    }

    @PutMapping
    ResponseEntity<Coffee> putCoffee(@RequestBody Coffee putCoffee) {
        return coffeeRepository.existsById(putCoffee.getId())
                ? new ResponseEntity<>(coffeeRepository.save(putCoffee), HttpStatus.OK)
                : new ResponseEntity<>(coffeeRepository.save(putCoffee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCoffee(@PathVariable String id) {
        if(coffeeRepository.existsById(id)) {
            coffeeRepository.deleteById(id);
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        }
        // Sent HTTP 204 based on https://tools.ietf.org/html/rfc7231#section-4.3.5 if there is no action
        // This seems better to me than just sending HTTP Status 200.
        return new ResponseEntity<>("No such record", HttpStatus.NO_CONTENT);
    }
}

