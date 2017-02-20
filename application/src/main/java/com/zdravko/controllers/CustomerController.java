package com.zdravko.controllers;

import com.zdravko.models.Customer;
import com.zdravko.models.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * Created by C5204250 on 4/20/2016.
 */
@Controller
@RequestMapping(value="/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    //Example: localhost:8181/customer/findall
    @RequestMapping(value="/findall")
    @ResponseBody
    public List<Customer> findall() {
        try {
            List<Customer> list = customerRepository.findAll();
            return list;
        } catch (Exception e) {
            //return e.getMessage();
        }

        return Collections.emptyList();
    }

    //Example: localhost:8181/customer/delete?id=2
    @RequestMapping(value="/delete")
    @ResponseBody
    public String delete(Long id) {
        try {
            Customer customer = customerRepository.findOne(id);
            customerRepository.delete(customer);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "User successfully deleted!";
    }

    //Example: localhost:8181/customer/deleteall
    @RequestMapping(value="/deleteall")
    @ResponseBody
    public String deleteall() {
        try {
            List<Customer> list = customerRepository.findAll();
            for(Customer customer : list) {
                customerRepository.delete(customer);
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "User successfully deleted!";
    }

    //Example: localhost:8181/customer/save?firstname=Zdravko&lastname=Rusinov&email=zdravko@ariba.com
    @RequestMapping(value="/save")
    @ResponseBody
    public String create(String firstname, String lastname, String email) {
        try {
            Customer customer = new Customer(firstname, lastname, email);
            customerRepository.save(customer);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
        return "User succesfully saved!";
    }

}
