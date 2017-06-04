package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
// Domain model이 network를 통해 전송되거나 disk에 저장될 때 field가 차례로 나열되어 저장된다.
public class Cart implements Serializable {

	// Class의 버전(Class 수정 시 버전이 바뀌어야 한다.)
	private static final long serialVersionUID = 1413783241344225820L;

	@Id
	@GeneratedValue
	private int cartId;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	private double grandTotal;
}
