package in.tatsam.priority.controller;

import in.tatsam.priority.model.Priority;
import in.tatsam.priority.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @GetMapping("/{id}")
    public ResponseEntity getAll(@PathVariable Long id){
        return ResponseEntity.ok(priorityService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(priorityService.findAll());
    }

    @PostMapping
    public ResponseEntity addPriority(@RequestBody Priority priority, @RequestHeader Long userId){
        return ResponseEntity.ok(priorityService.addPriority(priority, userId));
    }

    @PutMapping
    public ResponseEntity updatePriority(@RequestBody Priority priority, @RequestHeader Long userId){
        return ResponseEntity.ok(priorityService.updatePriority(priority, userId));
    }
}
