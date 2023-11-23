package com.nyal.minisof.controller;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.ProductCartEntity;
import com.nyal.minisof.model.ProductEntity;
import com.nyal.minisof.service.product.ProductService;
import com.nyal.minisof.service.product_cart.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ProductCartController {
    @Autowired
    ProductCartService productCartService;
    @Autowired
    ProductService productService;
    @GetMapping("/getProductCart")
    public ResponseEntity<ProductCartEntity> getProductCart(@RequestParam("product_cart_id") Integer productCartId){
        if (productCartService != null){
            ProductCartEntity productCart = productCartService.findById(productCartId).get();
            if (productCart != null){
                return new ResponseEntity<>(productCart, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/productCartByAccount")
    public ResponseEntity<List<ProductCartEntity>> getAllProductCartByAccount(@RequestParam("account_id") Integer account_id){
        if (productCartService != null){
             List<ProductCartEntity> productCartList = productCartService.getAllProductCartByAccountId(account_id);
             if (productCartList != null){
                 return new ResponseEntity<>(productCartList, HttpStatus.OK);
             } else {
                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
             }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getCart")
    public ResponseEntity<List<ProductCartEntity>> getCart(){
        if (productCartService != null){
            List<ProductCartEntity> productCartList = productCartService.findAll();
            if (productCartList != null){
                return new ResponseEntity<>(productCartList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addProductToCart")
    public ResponseEntity<Boolean> addProductToCart(@RequestParam("product_id") int productId, @RequestBody AccountEntity account){
        ProductEntity product = productService.findById(productId).get();
        if (productCartService != null){
            if (!productCartService.existByProductId(productId)){
                ProductCartEntity productCart = new ProductCartEntity();
                productCart.setQuantity(1);
                productCart.setProduct(product);
                productCart.setPrice(product.getPrice());
                productCart.setAccount(account);
                productCartService.save(productCart);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                ProductCartEntity productCart = productCartService.findByProductId(productId);
                productCart.setQuantity(productCart.getQuantity()+1);
                productCart.setPrice(productCart.getPrice()+product.getPrice());
                productCart.setProduct(product);
                productCart.setAccount(account);
                productCartService.save(productCart);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/editProductToCart")
    public ResponseEntity<Boolean> editProductToCart(@RequestBody ProductCartEntity productCart, @RequestBody AccountEntity account){
        if (productCartService != null && productCart != null){
            ProductCartEntity existingProductCart = productCartService.findById(productCart.getProductCartId()).get();
            if (existingProductCart != null){
                existingProductCart.setProductCartId(productCart.getProductCartId());
                existingProductCart.setProduct(productCart.getProduct());
                existingProductCart.setPrice(productCart.getPrice());
                existingProductCart.setQuantity(productCart.getQuantity());
                existingProductCart.setAccount(account);
                productCartService.save(existingProductCart);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteProductCart(@RequestParam("product_cart_id") int productCartId) {
        ProductCartEntity existingProductCart = productCartService.findById(productCartId).get();
        if (existingProductCart != null) {
            productCartService.delete(existingProductCart);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
