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
// Domain model�� network�� ���� ���۵ǰų� disk�� ����� �� field�� ���ʷ� �����Ǿ� ����ȴ�.
public class Cart implements Serializable {

	// Class�� ����(Class ���� �� ������ �ٲ��� �Ѵ�.)
	private static final long serialVersionUID = 1413783241344225820L;

	@Id
	@GeneratedValue
	private int cartId;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	private double grandTotal;
}
