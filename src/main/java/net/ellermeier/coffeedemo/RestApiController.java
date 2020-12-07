package net.ellermeier.coffeedemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestApiController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiController() {
        coffees.addAll(List.of(
                Coffee.of("Dark Coffee"),
                Coffee.of("Very Dark Coffee"),
                Coffee.of("Light Coffee")
        ));
    }

    @RequestMapping(value = "/coffees", method = RequestMethod.GET)
    Iterable<Coffee> getCoffes() {
        return this.coffees;
    }
}
