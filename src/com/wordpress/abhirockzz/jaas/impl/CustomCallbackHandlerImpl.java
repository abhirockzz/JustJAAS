package com.wordpress.abhirockzz.jaas.impl;

import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class CustomCallbackHandlerImpl implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {

		System.out.println("ENTER CustomCallbackHandlerImpl/handle");
		
		try(Scanner scanner = new Scanner(System.in);){
			for (int i = 0; i < callbacks.length; i++) {
				
				if(callbacks[i] instanceof NameCallback){
					NameCallback cBack =  (NameCallback) callbacks[i];
					System.out.println(cBack.getPrompt());
					
					String username = scanner.nextLine();
					
					
					
					cBack.setName(username);
					
				}else if(callbacks[i] instanceof PasswordCallback){
					PasswordCallback cBack =  (PasswordCallback) callbacks[i];
					System.out.println(cBack.getPrompt());
					//Scanner scanner = new Scanner(System.in);
					String password = scanner.nextLine();
					
					
					
					cBack.setPassword(password.toCharArray());
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("EXIT CustomCallbackHandlerImpl/handle");
		
	}

}
