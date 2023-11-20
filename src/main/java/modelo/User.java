package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
private Integer id;
private String username;
private String password;
private String email;


public User(String username, String password, String email) {
	this.username = username;
	this.password = password;
	this.email = email;
}




}
