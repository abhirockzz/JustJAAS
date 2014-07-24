package com.wordpress.abhirockzz.jaas.impl;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class CustomLoginModule implements LoginModule{
	
	private CallbackHandler callbackHandler = null;
	private boolean authenticated = false;
	private Subject subject;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;
	private boolean committed = false;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		System.out.println("ENTER CustomLoginModule/initialize");
		System.out.println(Thread.currentThread().getName());
		
		/*System.out.println(subject.isReadOnly());
		System.out.println(callbackHandler.getClass());
		System.out.println(sharedState);
		System.out.println(options);*/
		
		this.callbackHandler = callbackHandler;
		this.subject = subject;
		this.options = options;
		this.sharedState = sharedState;
		
		System.out.println("EXIT CustomLoginModule/initialize");
	}

	@Override
	public boolean login() throws LoginException {
		System.out.println("ENTER CustomLoginModule/login");
		
		boolean result = true;
		
		Callback nameCBack = new NameCallback("Username: ");
		Callback pwdCBack = new PasswordCallback("Password: ", false);
		
		Callback[] callbacks = new Callback[2];
		callbacks[0] = nameCBack;
		callbacks[1] = pwdCBack;
		
		try {
			callbackHandler.handle(callbacks);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} 
		
		String username = ((NameCallback) callbacks[0]).getName();
		System.out.println("Username entered by user: "+ username);
		
		String password = new String (((PasswordCallback) callbacks[1]).getPassword());
		System.out.println("Password entered by user: "+ password.toString());
		
		if((username.equals("Abhishek") && password.equals("Password"))){
			System.out.println("Credentials verified!!");			
		}else{
			result = false;
		}
		authenticated = result;
		System.out.println("CustomLoginModule/login returning :"+ result);
		System.out.println("EXIT CustomLoginModule/login");
		return result;
	}

	@Override
	public boolean commit() throws LoginException {
		System.out.println("ENTER CustomLoginModule/commit");
		
		if(!authenticated){
			return false;
		}else{
			committed = true;
		}
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		System.out.println("ENTER CustomLoginModule/abort");
		
		authenticated = false;
		committed = false;
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		System.out.println("ENTER CustomLoginModule/logout");
		
		authenticated = false;
		committed = false;
		return false;
	}

}
