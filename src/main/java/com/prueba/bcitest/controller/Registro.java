package com.prueba.bcitest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.bcitest.model.User;
import com.prueba.bcitest.service.Recoder;

@RestController
public class Registro {

	@Autowired
	Recoder recoder;

	@PostMapping("/registro")
	public ResponseEntity<Object> registrar(@RequestBody User NewUser) {
		return recoder.recordUser(NewUser);
	}

}
