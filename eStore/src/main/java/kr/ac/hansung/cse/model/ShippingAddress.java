package kr.ac.hansung.cse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class ShippingAddress {

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "foreign", parameters = @Parameter(value = "user", name = "property"))
	private int id;

	private String address;

	private String country;

	private String zipCode; // 우편번호

	@OneToOne
	@JoinColumn(name = "userId")
	private User user; // 양방향
}
