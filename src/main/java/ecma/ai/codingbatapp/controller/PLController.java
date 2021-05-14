package ecma.ai.codingbatapp.controller;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.service.PLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pl")
public class PLController {
    @Autowired
    PLService plService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(plService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        ProgrammingLanguage one = plService.getOne(id);
        return ResponseEntity.status(one == null ? HttpStatus.NO_CONTENT : HttpStatus.FOUND).body(one);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse delete = plService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody ProgrammingLanguage pl) {
        ApiResponse add = plService.add(pl);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(add);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse> edit(@Valid @PathVariable Integer id,@RequestBody ProgrammingLanguage pl){
        ApiResponse edit = plService.edit(id, pl);
        return ResponseEntity.status(edit.isSuccess()?201:209).body(edit);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
