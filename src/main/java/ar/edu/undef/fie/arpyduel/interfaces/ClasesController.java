package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindClaseCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ClaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/clase")
public class ClasesController {
    private final FindClaseCommandQuery find;


    public ClasesController(FindClaseCommandQuery find) {
        this.find = find;
    }
    @GetMapping("/all")
    public List<ClaseResponse> getClasesAll(){
        return find.getAll().
                stream().
                map(Clase::response).
                collect(Collectors.toList());
    }
}
