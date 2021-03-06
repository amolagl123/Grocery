package com.grocery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.model.CustomerAddress;
import com.grocery.repository.CustomerAddressRepository;

@RestController
@RequestMapping("/api/v1")
public class CustomerAddressController {

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@GetMapping(value = "/customer-address")
	public List<CustomerAddress> getAllCustomerAddress() {
		return customerAddressRepository.findAll();
	}

	@GetMapping(value = "/customer-address/{customerAddressId}")
	public Optional<CustomerAddress> getCustomersAddressById(
			@PathVariable("customerAddressId") Integer customerAddressId) {
		return customerAddressRepository.findById(customerAddressId);
	}
	
	@GetMapping(value = "/customer-address/customer/{customerId}")
	public List<CustomerAddress> getCustomersAddressByCustomerId(@PathVariable("customerId") Integer customerId) {
		return customerAddressRepository.findByCustomerId(customerId);
	}
	

	@PostMapping(value = "/customer-address")
	public CustomerAddress addCustomerAddress(@RequestBody CustomerAddress customerAddress) {
		return customerAddressRepository.save(customerAddress);
	}

	@PutMapping(value = "/customer-address")
	public CustomerAddress updateCustomerAddresss(@RequestBody CustomerAddress customerAddressRequest) {
		CustomerAddress customersAddress = customerAddressRepository.findById(customerAddressRequest.getCustAddressId()).get();
		customersAddress.setCity(customerAddressRequest.getCity());
		customersAddress.setStreet(customerAddressRequest.getStreet());
		customersAddress.setState(customerAddressRequest.getState());
		customersAddress.setCountry(customerAddressRequest.getCountry());
		return customerAddressRepository.save(customersAddress);
	}

	@DeleteMapping(value = "/customer-address/{customerAddressId}")
	public void deleteCustomeAddressrById(@PathVariable("customerAddressId") Integer id) {
		customerAddressRepository.deleteById(id);
	}

	@PutMapping(value = "/customer-address/mark-default/{customerAddressId}")
	public CustomerAddress markAddressAsDefault(@PathVariable("customerAddressId") Integer customerAddressId) {
		CustomerAddress customersAddr = customerAddressRepository.findById(customerAddressId).get();
		customersAddr.setIsDefault(customersAddr.getIsDefault()==false?true:customersAddr.getIsDefault());
		return customerAddressRepository.save(customersAddr);
	}

}
