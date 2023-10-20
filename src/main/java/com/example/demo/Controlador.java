package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
public class Controlador {

    @PostMapping("/register_products")
    public Product register_products(@RequestBody Product product) throws SQLException, ClassNotFoundException, ParseException {

        String code = product.getCode();
        String name = product.getName();
        String description = product.getDescription();
        String id_supplier = product.getId_supplier();

        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                description == null || description.equals("") || description.length() < 0 || id_supplier == null ||
                id_supplier.equals("") || id_supplier.length() < 0) {

            return new Product(null, null, null, null);
        } else {
            BD bd = new BD();
            String code_supplier = bd.Select_supplier(id_supplier);

            if (code_supplier.equals("")) {
                return new Product(null, null, null, Errors.error_register_supplier);
            } else {
                product = bd.Register_product(code, name, description, id_supplier);
            }
        }
        return product;
    }

    @PostMapping("/edit_product")
    public Product edit_product(@RequestBody Product product) throws SQLException, ClassNotFoundException {

        String code = product.getCode();
        String name = product.getName();
        String description = product.getDescription();
        String id_supplier = product.getId_supplier();


        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                description == null || description.equals("") || description.length() < 0 || id_supplier == null ||
                id_supplier.equals("") || id_supplier.length() < 0) {

            return new Product(null, null, null, null);
        } else {
            BD bd = new BD();
            String code_supplier = bd.Select_supplier(id_supplier);

            if (code_supplier.equals("")) {
                return new Product(null, null, null, Errors.error_id_supplier);
            } else {
                product = bd.edit_product(code, name, description, id_supplier);
            }
        }
        return product;
    }

    @GetMapping("/search_products")
    public List<Product> search_products() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        List<Product> list = bd.search_products();

        return list;
    }
    @GetMapping("/search/{code}")
    public Product search_product(@PathVariable String code) throws SQLException, ClassNotFoundException {
        BD bd = new BD();
        Product product;

        if (code == null || code.equals("") || code.length() < 0) {

            return new Product(Errors.error_search_one, null, null, null);

        } else {
            product = BD.search_product(code);
        }
        return product;
    }
    @DeleteMapping("/delete")
    public Product delete(@RequestBody Product product) throws SQLException, ClassNotFoundException {

        String code = product.getCode();
        if (product.getCode() == null || product.getCode().equals("") || product.getCode().length() < 0) {
            return new Product(null, null, null, null);
        } else {

            int f = BD.Delete(code);
            if (f == 0) {
                return new Product(Errors.error_delete, null, null, null);
            }
        }

        return product;
    }
    @DeleteMapping("/delete_all")
    public String delete_all() throws SQLException, ClassNotFoundException {

        BD bd = new BD();
        int f = BD.Delete();

        String message = "Se han eliminado todos los productos";
        return message;
    }
}
