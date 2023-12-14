package com.prueba.bcitest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.bcitest.model.User;

public interface UserInterface extends JpaRepository<User, Long>{
	//personalizamos la interfaz para poder filtrar busqueda por email
	List<User> findByEmail(String email);

}
