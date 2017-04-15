package testHibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

	// PK로 사용, 키값을 자동으로 생성
	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int id;

	private String name;
}
