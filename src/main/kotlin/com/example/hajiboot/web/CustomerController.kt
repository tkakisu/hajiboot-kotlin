package com.example.hajiboot.web

import com.example.hajiboot.domain.Customer
import com.example.hajiboot.service.CustomerService
import com.example.hajiboot.service.LoginUserDetails
import org.modelmapper.ModelMapper
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("customers")
class CustomerController(@Autowired val customerService: CustomerService,
                             @Autowired val modelMapper: ModelMapper) {
    @ModelAttribute
    fun setUpForm(): CustomerForm {
        return CustomerForm()
    }

    @GetMapping
    fun list(model: Model): String {
        val customers = customerService.findAll()
        model.addAttribute("customers", customers)
        return "customers/list"
    }

    @PostMapping(path = ["create"])
    fun create(@Validated form: CustomerForm, result: BindingResult, model: Model,
               @AuthenticationPrincipal userDetails: LoginUserDetails): String {
        if (result.hasErrors()) {
            return list(model)
        }
        val customer: Customer = modelMapper.map(form, Customer::class.java)
        customerService.create(customer, userDetails.user)
        return "redirect:/customers"
    }

    @GetMapping(path = ["edit"], params = ["form"])
    fun editForm(@RequestParam id: Int, form: CustomerForm): String {
        val customer = customerService.findOne(id)
        modelMapper.map(customer, form)
        return "customers/edit"
    }

    @PostMapping(path = ["edit"])
    fun edit(@RequestParam id: Int, @Validated form: CustomerForm, result: BindingResult,
             @AuthenticationPrincipal userDetails: LoginUserDetails): String {
        if (result.hasErrors()) {
            return editForm(id, form)
        }
        val customer: Customer = modelMapper.map(form, Customer::class.java)
        customer.id = id
        customerService.update(customer, userDetails.user)
        return "redirect:/customers"
    }

    @GetMapping(path = ["edit"], params = ["goToTop"])
    fun goToTop(): String {
        return "redirect:/customers"
    }

    @PostMapping(path = ["delete"])
    fun delete(@RequestParam id: Int): String {
        customerService.delete(id)
        return "redirect:/customers"
    }
}