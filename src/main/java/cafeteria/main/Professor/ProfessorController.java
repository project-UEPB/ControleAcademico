package cafeteria.main.Professor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository coffeeRepository;

    @GetMapping
    public List<Professor> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Professor> getCoffeeById(@PathVariable Long id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    public Professor createCoffee(@RequestBody Professor coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    public Professor updateCoffee(@PathVariable("id") Long id, @RequestBody Professor coffee) {
        return coffeeRepository.save(coffee);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id) {
        coffeeRepository.delete(coffeeRepository.findById(id).get());
    }
}