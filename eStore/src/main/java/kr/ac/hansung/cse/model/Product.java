package kr.ac.hansung.cse.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	private int id;

	@NotEmpty(message = "The product name must not be null")
	private String name;

	private String category;

	@Min(value = 0, message = "The product price must not bo less than zero")
	private int price;

	@NotEmpty(message = "The product manufacturer name must not be null")
	private String manufacturer;

	@Min(value = 0, message = "The product unit in stock must not bo less than zero")
	private int unitInStock;

	private String description;

	private MultipartFile productImage;

	private String imageFilename;
}