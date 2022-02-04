package com.vwits.gateway.model;

public class AuthResponse {

	 private String accessToken ;
	 private String username ;

	    public AuthResponse(String accessToken, String username) {
	        this.accessToken = accessToken;
	        this.username = username;
	    }
	    public AuthResponse(String accessToken) {
	        this.accessToken = accessToken;
	    }
	    public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public AuthResponse() {
	    }

	    public String getAccessToken() {
	        return accessToken;
	    }

	    public void setAccessToken(String accessToken) {
	        this.accessToken = accessToken;
	    }
}
