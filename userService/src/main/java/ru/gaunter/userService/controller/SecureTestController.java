package ru.gaunter.userService.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class SecureTestController {

    @GetMapping("/admin")
    public ResponseEntity<?> getAdmin(){
        return ResponseEntity.ok("*доступ к Админке");
    }

    @GetMapping("/shop")
    public ResponseEntity<?> getShop(){
        return ResponseEntity.ok("*доступ к магазину");
    }

    @GetMapping("/buyer")
    public ResponseEntity<?> getBuy(){
        return ResponseEntity.ok("*доступ к продуктам");
    }
}
