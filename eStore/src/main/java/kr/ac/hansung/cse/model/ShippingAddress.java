package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class ShippingAddress implements Serializable {

	private static final long serialVersionUID = 3273994294229576271L;

	@Id
	@GeneratedValue
	private int id;

	private String address;

	private String country;

	private String zipCode; // 우편번호

}
