package com.prueba.bcitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.bcitest.model.Phone;

public interface PhoneInterface extends JpaRepository<Phone, Long> {

}
