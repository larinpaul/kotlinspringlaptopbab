package com.example.kotlinspringlaptopbab;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {

//    private List<Coffee> coffees = new ArrayList<>();
//    public RestApiDemoController() {
//        coffees.addAll(List.of(
//                new Coffee("Café Cereza"),
//                new Coffee("Café Ganador"),
//                new Coffee("Café Lareño"),
//                new Coffee("Café Três Pontas")
//        ));
//    }

//    @GetMapping
//    Iterable<Coffee> getCoffees() {
//        return coffees;
//    }


//    @PostMapping
//    Coffee postCoffee(@RequestBody Coffee coffee) {
//        coffees.add(coffee);
//        return coffee;
//    }
//
//    @PutMapping("/{id}")
//    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
//        int coffeeIndex = -1;
//        for (Coffee c : coffees) {
//            if (c.getId().equals(id)) {
//                coffeeIndex = coffees.indexOf(c);
//                coffees.set(coffeeIndex, coffee);
//            }
//        }
//        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
//    }
//
//    @DeleteMapping("/{id}")
//    void deleteCoffee(@PathVariable String id) {
//        coffees.removeIf(c -> c.getId().equals(id));
//    }
//@GetMapping("/{id}")
//Optional<Coffee> getCoffeeById(@PathVariable String id) {
//    for (Coffee c : coffees) {
//        if (c.getId().equals(id)) {
//            return Optional.of(c);
//        }
//    }
//    return Optional.empty();
//}

    // Автосвязывание репозитория с RestApiDemoController

    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;

        this.coffeeRepository.saveAll(List.of(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cafe Tres Pontas")
        ));
    }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(
            @PathVariable String id, @RequestBody Coffee coffee
    ) {
        return (!coffeeRepository.existsById(id))
        ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED)
        : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }

}
