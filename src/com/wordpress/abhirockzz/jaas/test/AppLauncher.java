package com.wordpress.abhirockzz.jaas.test;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.wordpress.abhirockzz.jaas.impl.CustomCallbackHandlerImpl;

public class AppLauncher {

	public static void main(String[] args) {
		
		System.out.println("ENTER AppLauncher/main");
		
		System.setProperty("java.security.auth.login.config", "jaas.config");
		LoginContext loginContext = null;
		
		try {
			
			loginContext = new LoginContext("CustomLoginModule", new CustomCallbackHandlerImpl());
			
			System.out.println(Thread.currentThread().getName());
			System.out.println("LoginContext created");
			
		} catch (LoginException e) {
			System.err.println("Unable to create LoginContext!!!");
			e.printStackTrace();
		}
		
		try {
			loginContext.login();
			System.out.println("Logged in !!!");
		} catch (LoginException e) {
			System.err.println("Unable to login!!!");
			e.printStackTrace();
		}
	
		
		System.out.println("EXIT AppLauncher/main");
	}
}
