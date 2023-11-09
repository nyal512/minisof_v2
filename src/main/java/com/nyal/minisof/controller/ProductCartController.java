package com.nyal.minisof.controller;

import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.model.ProductCartEntity;
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
    public ResponseEntity<Boolean> addProductToCart(ProductCartEntity productCart){
        if (productCartService != null && productCart != null){
            if (!productCartService.existByProductId(productCart.getProduct().getProductId())){
                productCartService.save(productCart);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/editProductToCart")
    public ResponseEntity<Boolean> editProductToCart(ProductCartEntity productCart){
        if (productCartService != null && productCart != null){
            ProductCartEntity existingProductCart = productCartService.findById(productCart.getProductCartId()).get();
            if (existingProductCart != null){
                existingProductCart.setProductCartId(productCart.getProductCartId());
                existingProductCart.setProduct(productCart.getProduct());
                existingProductCart.setPrice(productCart.getPrice());
                existingProductCart.setQuantity(productCart.getQuantity());
                productCartService.save(existingProductCart);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@RequestParam("product_cart_id") int productCartId) {
        ProductCartEntity existingProductCart = productCartService.findById(productCartId).get();
        if (existingProductCart != null) {
            productCartService.delete(existingProductCart);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}