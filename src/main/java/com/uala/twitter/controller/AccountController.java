package com.uala.twitter.controller;

import com.uala.twitter.db.Account;
import com.uala.twitter.interfaces.IAccount;
import com.uala.twitter.interfaces.IFollow;
import com.uala.twitter.interfaces.IUsers;
import com.uala.twitter.utils.ResponseValidationsDTO;
import com.uala.twitter.utils.ValidationsUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@Tag(name = "Account", description = "Account Options")
public class AccountController {

    @Autowired
    private IFollow iFollow;

    @Autowired
    private IAccount iAccount;

    @Operation(summary = "Get number of followers")
    @GetMapping(value="/followers")
    public ResponseEntity<?> followers(@RequestHeader Integer id){
        if(id == null || id == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id is required");
        }
        return ResponseEntity.status(HttpStatus.OK).body(iFollow.getFollowers(id) + " people follow you");
    }

    @Operation(summary = "Get number of followings")
    @GetMapping(value="/following")
    public ResponseEntity<?> followings(@RequestHeader Integer id){
        if(id == null || id == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id is required");
        }
        return ResponseEntity.status(HttpStatus.OK).body("You are following " + iFollow.getFollowings(id) + " people");
    }

    @Operation(summary = "You can follow a specific user")
    @PostMapping(value="/follow")
    public ResponseEntity<?> followUser(@RequestHeader Integer id, @RequestHeader String nickname){
        if(id == null || id == 0 || nickname == null || nickname.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id and nickname are required");
        }

        if(iFollow.followUser(id, nickname))
            return ResponseEntity.status(HttpStatus.OK).body("You are following this user");
        else
            return ResponseEntity.status(HttpStatus.OK).body("Sorry, an error occurred, please try again");

    }

    @Operation(summary = "You can unfollow a specific user")
    @PostMapping(value="/unfollow")
    public ResponseEntity<?> unfollowUser(@RequestHeader Integer id, @RequestHeader String nickname){
        if(id == null || id == 0 || nickname == null || nickname.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id and nickname are required");
        }
        if(iFollow.unfollowUser(id, nickname))
            return ResponseEntity.status(HttpStatus.OK).body("You are not following this user");
        else
            return ResponseEntity.status(HttpStatus.OK).body("Sorry, an error occurred, please try again");
    }

    @Operation(summary = "You can make a new tweet")
    @PostMapping(value="/makeTweet")
    public ResponseEntity<?> makeTweet(@RequestBody Account tweet){

        ValidationsUtils validationsUtils = new ValidationsUtils();
        ResponseValidationsDTO response = validationsUtils.validateTweetInformation(tweet);

        if(!response.isState()) {
            System.out.println("-----> Status " + HttpStatus.ACCEPTED + " Message: " + response.getMessage());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.getMessage());
        }

        if(iAccount.makeTweet(tweet)) {
            System.out.println("-----> Status " + HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body("Your tweet has been published");
        }
        else {
            System.out.println("-----> Status " + HttpStatus.ACCEPTED);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Your tweet has not been published");
        }
    }

    @Operation(summary = "You can go to home and watch a timeline tweets users")
    @GetMapping(value="/home")
    public ResponseEntity<?> home(@RequestHeader Integer userId){
        if(userId == null || userId == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id is required");
        }
        return ResponseEntity.status(HttpStatus.OK).body(iAccount.getTweets(userId));
    }
}
