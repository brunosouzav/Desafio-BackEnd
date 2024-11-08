package com.desafioBack.criptografia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioBack.criptografia.entites.User;
import com.desafioBack.criptografia.exceptions.EmailAlreadyExistsException;
import com.desafioBack.criptografia.exceptions.UserNotFoundException;
import com.desafioBack.criptografia.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User createUser(User user) {
        
        if (repository.existsByGmail(user.getGmail())) {
            
            throw new EmailAlreadyExistsException("O gmail" + user.getGmail() + " já está cadastrado.");
        }

        return repository.save(user);
    }

	
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	public Optional<User> findById(Long id) {
	    if (!repository.existsById(id)) {
	        throw new UserNotFoundException("Usuário com id " + id + " não encontrado.");
	    }
	    return repository.findById(id);
	}

	public void delete(Long id) {
	    if (!repository.existsById(id)) {
	        throw new UserNotFoundException("Usuário com id " + id + " não encontrado.");
	    }
	    repository.deleteById(id);
	}

	
	public User updateUser(User user) {
	    Optional<User> existingUserOptional = repository.findById(user.getId());

	    if (existingUserOptional.isPresent()) {
	        User existingUser = existingUserOptional.get();
	        
	        existingUser.setGmail(user.getGmail());
	        existingUser.setPassword(user.getPassword());
	        existingUser.setUserDocument(user.getUserDocument());

	        return repository.save(existingUser);
	    } else {
	        throw new UserNotFoundException("Usuário com id " + user.getId() + " não encontrado.");
	    }
	}
}
