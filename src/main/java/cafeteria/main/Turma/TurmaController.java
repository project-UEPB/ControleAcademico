package cafeteria.main.Turma;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository coffeeRepository;

    @GetMapping
    public List<Turma> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Turma> getCoffeeById(@PathVariable Long id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    public Turma createCoffee(@RequestBody Turma coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    public Turma updateCoffee(@PathVariable("id") Long id, @RequestBody Turma coffee) {
        return coffeeRepository.save(coffee);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id) {
        coffeeRepository.delete(coffeeRepository.findById(id).get());
    }
}