package com.uala.twitter.controller;

import com.uala.twitter.db.Users;
import com.uala.twitter.interfaces.IUsers;
import com.uala.twitter.utils.ResponseValidationsDTO;
import com.uala.twitter.utils.ValidationsUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@Tag(name = "User", description = "User Controller")
public class UserController {

    @Autowired
    private IUsers iUsers;

    @Operation(summary = "Create users")
    @PostMapping(value="/")
    public ResponseEntity<?> createUser(@RequestBody Users user) {

        ValidationsUtils validationsUtils = new ValidationsUtils();
        ResponseValidationsDTO validations = validationsUtils.validateUserData(user);

        if(!validations.isState())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(validations.getMessage());
        else
            return ResponseEntity.status(HttpStatus.OK).body(iUsers.createUser(user));

    }

    @Operation(summary = "Get users list")
    @GetMapping(value="/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(iUsers.getUsers());
    }

    @Operation(summary = "Get a user by id")
    @GetMapping(value="/byId")
    public ResponseEntity<?> getUserById(@RequestHeader Integer id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id is required");
        } else {
            Users user = iUsers.getUserById(id);

            if(user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

            return ResponseEntity.status(HttpStatus.OK).body(iUsers.getUserById(id));
        }
    }

    @Operation(summary = "Get a user by nickname")
    @GetMapping(value="/byNickname")
    public ResponseEntity<?> getUserByNickname(@RequestHeader String nickname) {
        if(nickname == null || nickname.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("nickname is required");
        } else {
            Users user = iUsers.getUserByNickname(nickname);

            if(user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

            return ResponseEntity.status(HttpStatus.OK).body(iUsers.getUserByNickname(nickname));
        }
    }
}
