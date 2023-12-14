package com.prueba.bcitest.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long phone_id;
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	private Long number;
	private Long citycode;
	private Long contrycode;
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Long getCitycode() {
		return citycode;
	}
	public void setCitycode(Long citycode) {
		this.citycode = citycode;
	}
	public Long getContrycode() {
		return contrycode;
	}
	public void setContrycode(Long contrycode) {
		this.contrycode = contrycode;
	}
	
	public User getUser() { 
		return user; 
	}
    
	public void setUser(User user) { 
		this.user = user; 
    }
	
	public Long getId() {
		return phone_id;
	}
	
}
