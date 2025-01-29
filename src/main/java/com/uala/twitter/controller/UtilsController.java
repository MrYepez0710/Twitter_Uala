package com.uala.twitter.controller;

import com.uala.twitter.db.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/version")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@Tag(name = "Version", description = "Version Controller")
public class UtilsController {

    @Operation(summary = "Get a app version")
    @GetMapping(value="/")
    public ResponseEntity<?> getVersion() {
        return ResponseEntity.status(HttpStatus.OK).body("Versión 1.0.0");
    }
}
