package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindClassCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ClassResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/classes")
public class ClassController {
    private final FindClassCommandQuery classQuery;
    public ClassController(FindClassCommandQuery classQuery) {
        this.classQuery = classQuery;
    }
    @GetMapping("")
    public ResponseEntity<List<ClassResponse>> getClassesAll(){
        var response = classQuery.getAll().stream().map(CharacterClass::response).toList();
        return ResponseEntity.ok(response);
    }
}
