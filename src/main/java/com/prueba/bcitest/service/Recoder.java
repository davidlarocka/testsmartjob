package com.prueba.bcitest.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba.bcitest.model.Phone;
import com.prueba.bcitest.model.User;
import com.prueba.bcitest.repository.PhoneInterface;
import com.prueba.bcitest.repository.UserInterface;

@Service
public class Recoder {

	@Autowired
	UserInterface users;
	@Autowired
	PhoneInterface phones;

	@Value("${regex.clave}")
	private String regexclave;

	public ResponseEntity<Object> recordUser(User NewUser) {

		Map<String, Object> msg = new HashMap<>();

		// validar email no repetido
		if (!users.findByEmail(NewUser.getEmail()).isEmpty()) {
			msg.put("mensaje", "El correo ya registrado");
			return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
		} else {

			// validar email valido
			if (!this.validMail(NewUser.getEmail())) {
				msg.put("mensaje", "El correo no tiene formato valido");
				return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
			}

			// validar formato clave valido
			if (!this.patternMatches(NewUser.getPassword(), regexclave)) {
				msg.put("mensaje", "la clave debe contener un formato valido");
				return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
			}

			// obtenemos los numeros de tlf del usuario aún no creado
			List<Phone> ph = NewUser.getPhones();
			NewUser.setPhones(null);

			// completamos datos de sistema
			NewUser.setCreated(Instant.now().getEpochSecond());
			NewUser.setModified(Instant.now().getEpochSecond());
			NewUser.setLast_login(Instant.now().getEpochSecond());
			NewUser.setIsActive(true);

			// generamos token Jwt
			Tokenizar tokens = new Tokenizar();
			NewUser.setToken(tokens.generatedToken(NewUser));
			// persistimos el usuario en bd
			User userSaved = users.save(NewUser);

			// guardamos los numeros de telefono
			for (Phone p : ph) {
				p.setUser(userSaved);
				phones.save(p);
			}

			msg.put("mensaje", "usuario creado con éxito");
			msg.put("data", userSaved.showInfoUser());
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}

	}

	private boolean validMail(String email) {
		return this.patternMatches(email, "^(.+)@(\\S+)$");
	}

	private boolean patternMatches(String valor, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(valor).matches();
	}

}
