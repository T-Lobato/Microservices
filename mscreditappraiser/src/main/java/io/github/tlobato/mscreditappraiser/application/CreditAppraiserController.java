package io.github.tlobato.mscreditappraiser.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-assessments")
public class CreditAppraiserController {

    @GetMapping
    public String status(){
        return "ok";
    }
}