package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.WindowEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Window Controller", description = "APIs for managing windows")
@RestController
@RequestMapping("/api/windows")
public class WindowController {

    private List<WindowEntity> windowList = new ArrayList<>();

    @Operation(summary = "Retrieve a list of windows", description = "Fetch all windows in the system")
    @GetMapping
    public List<WindowEntity> getWindows() {
        return windowList;
    }

    @Operation(summary = "Retrieve a window by ID", description = "Find a window using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<WindowEntity> getWindowById(@PathVariable Long id) {
        Optional<WindowEntity> window = windowList.stream().filter(w -> w.getId().equals(id)).findFirst();
        return window.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create a new window", description = "Create a new window and add it to the system")
    @PostMapping
    public ResponseEntity<WindowEntity> createWindow(@RequestBody WindowEntity windowEntity) {
        windowEntity.setId((long) (windowList.size() + 1)); // Simple ID assignment for demo
        windowList.add(windowEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(windowEntity);
    }

    @Operation(summary = "Update an existing window", description = "Update the details of an existing window")
    @PutMapping("/{id}")
    public ResponseEntity<WindowEntity> updateWindow(@PathVariable Long id, @RequestBody WindowEntity updatedWindow) {
        Optional<WindowEntity> existingWindow = windowList.stream().filter(w -> w.getId().equals(id)).findFirst();
        if (existingWindow.isPresent()) {
            WindowEntity window = existingWindow.get();
            window.setWindowStatus(updatedWindow.getWindowStatus());
            return ResponseEntity.ok(window);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Delete a window by ID", description = "Delete a window using its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWindow(@PathVariable Long id) {
        Optional<WindowEntity> window = windowList.stream().filter(w -> w.getId().equals(id)).findFirst();
        if (window.isPresent()) {
            windowList.remove(window.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}