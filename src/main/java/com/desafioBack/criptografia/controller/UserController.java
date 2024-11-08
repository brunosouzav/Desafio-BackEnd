package com.desafioBack.criptografia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioBack.criptografia.entites.User;
import com.desafioBack.criptografia.exceptions.UserNotFoundException;
import com.desafioBack.criptografia.service.EncryptionService;
import com.desafioBack.criptografia.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private EncryptionService encryptionService;
	
	 @PostMapping 
	    public ResponseEntity<User> createUser(@RequestBody User user) {
	        
	        user.setUserDocument(encryptionService.hashWithSHA256(user.getUserDocument()));
	        user.setCreditCardToken(encryptionService.hashWithSHA256(user.getCreditCardToken()));

	        User newUser = service.createUser(user);

	        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	    }

	@GetMapping
	public ResponseEntity<List<User>> findAllUser(){
		
		List<User> users = service.findAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK );
	}

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserId(@PathVariable Long id) {
        User user = service.findById(id)
                           .orElseThrow(() -> new UserNotFoundException("Usuário com id " + id + " não encontrado."));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);  
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);  
        User updatedUser = service.updateUser(user);
        return ResponseEntity.ok(updatedUser);  
    }
	
}
