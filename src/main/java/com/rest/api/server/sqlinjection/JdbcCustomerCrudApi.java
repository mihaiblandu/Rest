package com.rest.api.server.sqlinjection;

import com.rest.entity.JdbcCustomer;
import com.rest.repository.JdbcCustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/api/sqlinjection/crud/v1")
@Validated
public class JdbcCustomerCrudApi {

    @Autowired
    private JdbcCustomerCrudRepository repository;

    @GetMapping(value = "/customer/{email}")
    public JdbcCustomer findCustomerByEmail(@PathVariable(required = true, name = "email") String email) {
        var queryResult = repository.findByEmail(email);

        if (queryResult != null && !queryResult.isEmpty()) {
            return queryResult.get(0);
        }

        return null;
    }

    @GetMapping(value = "/customer")
    public List<JdbcCustomer> findCustomersByGender(
            @Pattern(regexp = "^[MF]$", message = "Invalid gender") @RequestParam(required = true, name = "genderCode") String genderCode) {
        return repository.findByGender(genderCode);
    }

    @PostMapping(value = "/customer")
    public void createCustomer(@RequestBody(required = true) @Valid JdbcCustomer newCustomer) {
        repository.save(newCustomer);
    }



}