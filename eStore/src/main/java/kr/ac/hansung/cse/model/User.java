package kr.ac.hansung.cse.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private int userId;

	@NotEmpty(message = "The username must not be null")
	private String username;

	@NotEmpty(message = "The password must not be null")
	private String password;

	@NotEmpty(message = "The email must not be null")
	private String email;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private ShippingAddress shippingAddress; // 배송 주소

	private boolean enabled = false; // 가입자가 활성화 되어 있는지

	private String authority; // 권한
}
