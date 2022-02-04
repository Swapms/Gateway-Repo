package com.vwits.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.vwits.gateway.model.ApiResponse;
import com.vwits.gateway.model.AuthResponse;
import com.vwits.gateway.model.LoginRequest;
import com.vwits.gateway.model.User;
import com.vwits.gateway.service.ILoginService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

	 @Autowired
	    private ILoginService iLoginService;
 
	    @PostMapping("/signin")
	    @ResponseBody
	    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
	        String token = iLoginService.login(loginRequest.getUsername(),loginRequest.getPassword());
	        /*HttpHeaders headers = new HttpHeaders();
	        List<String> headerlist = new ArrayList<>();
	        List<String> exposeList = new ArrayList<>();
	        headerlist.add("Content-Type");
	        headerlist.add(" Accept");
	        headerlist.add("X-Requested-With");
	        headerlist.add("Authorization");
	        headers.setAccessControlAllowHeaders(headerlist);
	        exposeList.add("Authorization");
	        headers.setAccessControlExposeHeaders(exposeList);
	        headers.set("Authorization", token);*/
	        return new ApiResponse<>(200, "success",new AuthResponse(token,loginRequest.getUsername()));
	        //return new ResponseEntity<AuthResponse>(new AuthResponse(token,loginRequest.getUsername()), headers, HttpStatus.CREATED);
	    }
	   
	    @PostMapping("/signup")
	    @ResponseBody
	    public User signUp(@RequestBody User user) {
	        User user1 = iLoginService.saveUser(user);
	        return user1;
	    }
	    
	    @PostMapping("/signout")
	    @ResponseBody
	    public ResponseEntity<AuthResponse> logout (@RequestHeader(value="Authorization") String token) {
	        HttpHeaders headers = new HttpHeaders();
	      if (iLoginService.logout(token)) {
	          headers.remove("Authorization");
	          return new ResponseEntity<AuthResponse>(new AuthResponse("logged out"), headers, HttpStatus.CREATED);
	      }
	        return new ResponseEntity<AuthResponse>(new AuthResponse("Logout Failed"), headers, HttpStatus.NOT_MODIFIED);
	    }

	    /**
	     *
	     * @param token
	     * @return boolean.
	     * if request reach here it means it is a valid token.
	     */
	    @PostMapping("/valid/token")
	    @ResponseBody
	    public Boolean isValidToken (@RequestHeader(value="Authorization") String token) {
	        return true;
	    }


	    @PostMapping("/signin/token")
	    @ResponseBody
	    public ResponseEntity<AuthResponse> createNewToken (@RequestHeader(value="Authorization") String token) {
	        String newToken = iLoginService.createNewToken(token);
	        HttpHeaders headers = new HttpHeaders();
	        List<String> headerList = new ArrayList<>();
	        List<String> exposeList = new ArrayList<>();
	        headerList.add("Content-Type");
	        headerList.add(" Accept");
	        headerList.add("X-Requested-With");
	        headerList.add("Authorization");
	        headers.setAccessControlAllowHeaders(headerList);
	        exposeList.add("Authorization");
	        headers.setAccessControlExposeHeaders(exposeList);
	        headers.set("Authorization", newToken);
	        return new ResponseEntity<AuthResponse>(new AuthResponse(newToken), headers, HttpStatus.CREATED);
	    }
	    
}
