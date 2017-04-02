package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

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

	//
	// @RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	// public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
	// if(result.hasErrors()) {
	// System.out.println("===From data has some errors===");
	// List<ObjectError> errors = result.getAllErrors();
	// for(ObjectError error:errors) {
	// System.out.println(error.getDefaultMessage());
	// }
	// return "addProduct";
	// }
	//
	// MultipartFile productImage = product.getProductImage();
	// String rootDirectory = request.getSession().getServletContext().getRealPath("/");
	// Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
	// if(productImage != null && !productImage.isEmpty()) {
	// try {
	// productImage.transferTo(new File(savePath.toString()));
	// }
	// catch(Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// product.setImageFilename(productImage.getOriginalFilename());
	//
	//// if(productImage.isEmpty() == false) {
	//// System.out.println("------ file start ------");
	//// System.out.println("name: " + productImage.getName());
	//// System.out.println("filename: " + productImage.getOriginalFilename());
	//// System.out.println("filrsize: " + productImage.getSize());
	//// System.out.println("------ file start ------");
	//// }
	////
	// if( !productService.addProduct(product)) {
	// System.out.println("Adding product cannot be done");
	// }
	//
	// return "redirect:/admin/productInventory";
	// }
	//
	// @RequestMapping("/productInventory/deleteProduct/{id}")
	// public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
	//
	// Product product = productService.getProductById(id);
	//
	// String rootDirectory = request.getSession().getServletContext().getRealPath("/");
	// Path path = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());
	//
	// if(Files.exists(path)) {
	// try {
	// Files.delete(path);
	// }
	// catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	//
	// if(! productService.deleteProductById(id)) {
	// System.out.println("Deleting product cannot be done");
	// }
	//
	// return "redirect:/admin/productInventory";
	// }
	//
	// @RequestMapping("/productInventory/editProduct/{id}")
	// public String editProduct(@PathVariable int id, Model model) {
	// Product product = productService.getProductById(id);
	//
	// model.addAttribute("product", product);
	//
	// return "editProduct";
	// }
	//
	// @RequestMapping(value="/productInventory/editProduct", method=RequestMethod.POST)
	// public String editProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {
	//
	// if(result.hasErrors()) {
	// System.out.println("===From data has some errors===");
	// List<ObjectError> errors = result.getAllErrors();
	// for(ObjectError error:errors) {
	// System.out.println(error.getDefaultMessage());
	// }
	// return "editProduct";
	// }
	//
	// MultipartFile productImage = product.getProductImage();
	// String rootDirectory = request.getSession().getServletContext().getRealPath("/");
	// Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
	// if(productImage != null && !productImage.isEmpty()) {
	// try {
	// productImage.transferTo(new File(savePath.toString()));
	// }
	// catch(Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// product.setImageFilename(productImage.getOriginalFilename());
	//
	// if(! productService.editProduct(product)) {
	// System.out.println("Editing product cannot be done");
	// }
	//
	// return "redirect:/admin/productInventory";
	// }

}
