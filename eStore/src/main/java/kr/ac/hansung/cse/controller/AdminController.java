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

	// db를 조회해서 products list를 가져온 후 model에 저장해서 view에 전달한다.
	@RequestMapping("/productInventory")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "productInventory";
	}

	// products를 추가할 수 있도록 하는 페이지 구성.
	// 여기의 model를 "addProcuct"view에서 참조하여 control form에 내용을 채워준다.
	// 즉, 여기 model에 들어가 있는 객체와 나중에 view의 control들과 자동으로 matching된다.
	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		// 반드시 하나 생성해서 넘겨 줘야함. (빈 객체라도 생성해야 함.)
		// 나중에 view에서 spring form tag를 사용하는데 그때 서로 matching해야 하기 때문
		Product product = new Product();

		// 초기값 지정
		product.setName("노트북");
		product.setCategory("컴퓨터");
		model.addAttribute("product", product);

		return "addProduct";
	}

	// 실제 사용자가 form에 입력한 data를 DB에 저장한다.
	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
		/* 검증에 대한 error 체크 */
		if (result.hasErrors()) {
			System.out.println("===From data has some errors===");
			// 검증결과를 List형태로 받아온다.
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				// 현재는 console창에만 출력하도록
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}

		/* 넘어온 이미지를 실제 /resources/images에 저장한다. */
		MultipartFile productImage = product.getProductImage();
		// root directory를 동적으로 얻어온다.
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// 파일을 저장할 위치
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 파일 이름을 지정
		product.setImageFilename(productImage.getOriginalFilename());

		// /* 어떤 data binding에 의해 가져온 imageFile에 어떤 정보가 있는지 test */
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

	// 관리자가 product를 삭제하면 DB에 반영한다.
	@RequestMapping("/productInventory/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
		/* id에 해당하는 product를 DB에서 가져온 후 resources에 있는 image와 DB에서 product에 대한 것을 삭제한다. */
		Product product = productService.getProductById(id);

		// root directory를 동적으로 얻어온다.
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// 파일을 저장할 위치
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

	// product를 선택하여 내용을 수정할 수 있도록 한다.
	@RequestMapping("/productInventory/editProduct/{id}")
	public String editProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);

		model.addAttribute("product", product);

		return "editProduct";
	}

	// product를 선택하여 수정한 내용을 DB에 반영한다.
	@RequestMapping(value = "/productInventory/editProduct", method = RequestMethod.POST)
	public String editProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
		/* 검증에 대한 error체크 */
		if (result.hasErrors()) {
			System.out.println("===From data has some errors===");
			// 검증결과를 List형태로 받아온다.
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				// 현재는 console창에만 출력하도록
				System.out.println(error.getDefaultMessage());
			}
			return "editProduct";
		}

		/* 넘어온 이미지를 실제 /resources/images에 저장한다. */
		MultipartFile productImage = product.getProductImage();
		// root directory를 동적으로 얻어온다.
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// 파일을 저장할 위치
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 파일 이름을 지정
		product.setImageFilename(productImage.getOriginalFilename());

		productService.editProduct(product);

		return "redirect:/admin/productInventory";
	}

}
