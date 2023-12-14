package com.prueba.bcitest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "users")
public class User {

	@Id
	@UuidGenerator
	private UUID user_id;
	
	@Column(unique=true)
	private String email;
	private String name;
	private String token;
	private String password;
	
	private long created;
	private long modified;
	private long last_login;
	
	@Column(columnDefinition = "boolean default true")
	boolean isactive;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Phone> phones;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public List<Phone> getPhones(){
		return this.phones;
	}
	
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getModified() {
		return modified;
	}
	public void setModified(long modified) {
		this.modified = modified;
	}
	public long getLast_login() {
		return last_login;
	}
	public void setLast_login(long last_login) {
		this.last_login = last_login;
	}
	
	public UUID getUserId() {
		return user_id;
	}
	
	public boolean getIsActive() {
		return isactive;
	}
	
	public void setIsActive(boolean isactive) {
		this.isactive = isactive;
	}
	
	public Map<String, Object> showInfoUser() {
		Map<String, Object> info = new HashMap<>();
		info.put("id", this.getUserId());
		info.put("name", this.getName());
		info.put("password", this.getPassword());
		info.put("email", this.getEmail());
		info.put("token", this.getToken());
		info.put("created", this.getCreated());
		info.put("modified", this.getModified());
		info.put("last_login", this.getLast_login());
		info.put("isactive", this.getIsActive());
		return info;
	}
	
}
