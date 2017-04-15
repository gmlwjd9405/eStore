package testHibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Product")
public class Product {

	// PK로 사용, 키값을 자동으로 생성
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private int id;

	@Column(name = "product_name")
	private String name;
	private int price;
	private String description;
	
	// FK로 사용
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
}
