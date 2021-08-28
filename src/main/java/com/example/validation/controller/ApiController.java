package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    // user를 받아서 user를 리턴하는 에코?형태
    @PostMapping("/user")   // @Valid 어노테이션이 달려있는 객체는 그 안에 어노테이션을 검사하고 검증하고 맞지 않으면 에러가 발생한다.
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){  // Validation 결과가 BindingResult에 들어옴

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field :"+field.getField());
                System.out.println(message);

                sb.append("field : "+field.getField());
                sb.append("message : "+message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println(user);

//        if(user.getAge() >= 90){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }

        return ResponseEntity.ok(user);
    }
}
