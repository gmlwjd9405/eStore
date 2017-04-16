package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductService productService;

	@RequestMapping
	public String adminPage() {
		return "admin";
	}

	// db�� ��ȸ�ؼ� products list�� ������ �� model�� �����ؼ� view�� �����Ѵ�.
	@RequestMapping("/productInventory")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "productInventory";
	}

	// products�� �߰��� �� �ֵ��� �ϴ� ������ ����.
	// ������ model�� "addProcuct"view���� �����Ͽ� control form�� ������ ä���ش�.
	// ��, ���� model�� �� �ִ� ��ü�� ���߿� view�� control��� �ڵ����� matching�ȴ�.
	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		// �ݵ�� �ϳ� �����ؼ� �Ѱ� �����. (�� ��ü�� �����ؾ� ��.)
		// ���߿� view���� spring form tag�� ����ϴµ� �׶� ���� matching�ؾ� �ϱ� ����
		Product product = new Product();

		// �ʱⰪ ����
		product.setName("��Ʈ��");
		product.setCategory("��ǻ��");
		model.addAttribute("product", product);

		return "addProduct";
	}

	// ���� ����ڰ� form�� �Է��� data�� DB�� �����Ѵ�.
	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
		/* ������ ���� error üũ */
		if (result.hasErrors()) {
			System.out.println("===From data has some errors===");
			// ��������� List���·� �޾ƿ´�.
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				// ����� consoleâ���� ����ϵ���
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}

		/* �Ѿ�� �̹����� ���� /resources/images�� �����Ѵ�. */
		MultipartFile productImage = product.getProductImage();
		// root directory�� �������� ���´�.
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// ������ ������ ��ġ
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ���� �̸��� ����
		product.setImageFilename(productImage.getOriginalFilename());

		// /* � data binding�� ���� ������ imageFile�� � ������ �ִ��� test */
		// if (productImage.isEmpty() == false) {
		// System.out.println("------ file start ------");
		// System.out.println("name: " + productImage.getName());
		// System.out.println("filename: " + productImage.getOriginalFilename());
		// System.out.println("filesize: " + productImage.getSize());
		// System.out.println("------ file start ------");
		// }

		productService.addProduct(product);

		return "redirect:/admin/productInventory";
	}

	// �����ڰ� product�� �����ϸ� DB�� �ݿ��Ѵ�.
	@RequestMapping("/productInventory/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
		/* id�� �ش��ϴ� product�� DB���� ������ �� resources�� �ִ� image�� DB���� product�� ���� ���� �����Ѵ�. */
		Product product = productService.getProductById(id);

		// root directory�� �������� ���´�.
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// ������ ������ ��ġ
		Path path = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());

		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		productService.deleteProduct(product);

		return "redirect:/admin/productInventory";
	}

	// product�� �����Ͽ� ������ ������ �� �ֵ��� �Ѵ�.
	@RequestMapping("/productInventory/editProduct/{id}")
	public String editProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);

		model.addAttribute("product", product);

		return "editProduct";
	}

	// product�� �����Ͽ� ������ ������ DB�� �ݿ��Ѵ�.
	@RequestMapping(value = "/productInventory/editProduct", method = RequestMethod.POST)
	public String editProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
		/* ������ ���� errorüũ */
		if (result.hasErrors()) {
			System.out.println("===From data has some errors===");
			// ��������� List���·� �޾ƿ´�.
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				// ����� consoleâ���� ����ϵ���
				System.out.println(error.getDefaultMessage());
			}
			return "editProduct";
		}

		/* �Ѿ�� �̹����� ���� /resources/images�� �����Ѵ�. */
		MultipartFile productImage = product.getProductImage();
		// root directory�� �������� ���´�.
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// ������ ������ ��ġ
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ���� �̸��� ����
		product.setImageFilename(productImage.getOriginalFilename());

		productService.editProduct(product);

		return "redirect:/admin/productInventory";
	}

}
