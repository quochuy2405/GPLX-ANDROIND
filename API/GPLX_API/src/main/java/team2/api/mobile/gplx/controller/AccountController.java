package team2.api.mobile.gplx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.google.gson.Gson;

import team2.api.mobile.gplx.dto.DtoLogin;
import team2.api.mobile.gplx.dto.LoginResponse;
import team2.api.mobile.gplx.dto.RegisterResponse;
import team2.api.mobile.gplx.dto.SignupDto;
import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.service.interfaces.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("api/account")
	public ResponseEntity<Object> GetAll() {
		List<Account> accounts = service.findAll();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@PostMapping("api/account/add")
	public ResponseEntity<Object> Post(@RequestBody Account account) {
		Account newAccount = service.save(account);
		if (newAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(newAccount, HttpStatus.OK);
	}

	@PutMapping("api/account/edit/{id}")
	public ResponseEntity<Object> Put(@PathVariable("id") String id,@RequestBody Account account) {
		Account updatedAccount = service.update(account, id);
		if (updatedAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}

	@DeleteMapping("api/account/delete/{id}")
	public ResponseEntity<Object> Delete(@PathVariable("id") String id){
		try {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("api/account/login")
	public ResponseEntity<Object> Login(@RequestBody DtoLogin dto){
		LoginResponse acc = service.Login(dto);
		if(acc == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}
	
	@PostMapping("api/account/signup")
	public ResponseEntity<Object> Register(@RequestBody SignupDto dto) {
		RegisterResponse response = new RegisterResponse();
		List<String> res = service.Register(dto);
		for(String item : res) {
			if(item.equals("Email")) response.setEmail("Email");
			else if(item.equals("Username")) response.setUsername("Username");
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
