package com.uala.twitter.utils;

import com.uala.twitter.db.Account;
import com.uala.twitter.db.Users;

public class ValidationsUtils {

    public ResponseValidationsDTO validateUserData(Users user) {

        ResponseValidationsDTO response = new ResponseValidationsDTO();
        response.setState(true);

        if(user == null){
            response.setMessage("User is null");
            response.setState(false);
            return response;
        }
        if(user.getFirstName().isEmpty()){
            response.setMessage("First name is empty");
            response.setState(false);
        }
        if(user.getLastName().isEmpty()){
            response.setMessage("Last name is empty");
            response.setState(false);
        }
        if(user.getNickname().isEmpty()){
            response.setMessage("Nick is empty");
            response.setState(false);
        }
        if(user.getEmail().isEmpty()){
            response.setMessage("Email is empty");
            response.setState(false);
            return response;
        }
        if(!user.getEmail().matches("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}")){
            response.setMessage("Email is invalid");
            response.setState(false);
        }

        return response;
    }

    public ResponseValidationsDTO validateTweetInformation(Account tweetInfo) {
        ResponseValidationsDTO response = new ResponseValidationsDTO();
        response.setState(true);

        if (tweetInfo == null) {
            response.setMessage("Tweet information is null");
            response.setState(false);
            return response;
        }
        if (tweetInfo.getUserId() == null || tweetInfo.getUserId() == 0) {
            response.setMessage("User id is null");
            response.setState(false);
        }
        if (tweetInfo.getTweets() == null || tweetInfo.getTweets().isEmpty()) {
            System.out.println("-----> " + tweetInfo.getTweets());
            response.setMessage("Tweet is empty");
            response.setState(false);
            return response;
        }
        if (tweetInfo.getTweets().length()>=280) {
            response.setMessage("Tweet is too long");
            response.setState(false);
        }

        return response;
    }
}
