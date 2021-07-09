package cybersoft.java12.crmapp.service;

import cybersoft.java12.crmapp.repository.UserRepository;

public class UserService {
	private UserRepository repository;
	
	public UserService() {
		repository = new UserRepository();
	}
	
	public boolean checkUser(String email, String password) {
		boolean result =repository.checkUser(email, password);
		System.out.println(result);
		return result;
	}
}
