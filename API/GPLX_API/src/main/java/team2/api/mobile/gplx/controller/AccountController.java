package team2.api.mobile.gplx.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import team2.api.mobile.gplx.dto.ChangePassword;

//import com.google.gson.Gson;

import team2.api.mobile.gplx.dto.DtoLogin;
import team2.api.mobile.gplx.dto.LoginResponse;
import team2.api.mobile.gplx.dto.RegisterResponse;
import team2.api.mobile.gplx.dto.SignupDto;
import team2.api.mobile.gplx.dto.VerificationCode;
import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.service.interfaces.AccountService;
import team2.api.mobile.gplx.utils.EmailConstant;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	@Autowired
	private JavaMailSender emailSender;

	@GetMapping("api/account")
	public ResponseEntity<Object> GetAll() {
		List<Account> accounts = service.findAll();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("api/account/{id}")
	public ResponseEntity<Object> GetById(@PathVariable("id") String id) {
		try {
			Account account = service.findById(id).get();
			return new ResponseEntity<>(account, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("api/account/add")
	public ResponseEntity<Object> AddNewAccount(@RequestBody Account account) {
		Account newAccount = service.save(account);
		if (newAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(newAccount, HttpStatus.OK);
	}

	@PatchMapping("api/account/edit/{id}")
	public ResponseEntity<Object> UpdateAccount(@PathVariable("id") String id, @RequestBody Account account) {
		Account updatedAccount = service.update(id, account);
		System.out.println(account.toString());
		if (updatedAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}

	@DeleteMapping("api/account/delete/{id}")
	public ResponseEntity<Object> DeleteAccount(@PathVariable("id") String id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("api/account/login")
	public ResponseEntity<Object> Login(@RequestBody DtoLogin dto) {
		LoginResponse acc = service.Login(dto);
		if (acc == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(acc, HttpStatus.OK);
	}

	@PostMapping("api/account/signup")
	public ResponseEntity<Object> Register(@RequestBody SignupDto dto) {
		RegisterResponse response = new RegisterResponse();
		List<String> res = service.Register(dto);
		for (String item : res) {
			if (item.equals("Email"))
				response.setEmail("Email");
			else if (item.equals("Username"))
				response.setUsername("Username");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// Hàm này để test gửi email
	@ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(EmailConstant.FRIEND_EMAIL);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }
	
	@GetMapping("api/account/forgotpass/{email}")
	public ResponseEntity<Object> ForgotPass(@PathVariable("email") String email) {
		
		SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(email);
        message.setSubject("Verification code");
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        VerificationCode code = new VerificationCode(String.valueOf(randomNum));
        message.setText("This is your verification code: " + randomNum);

        // Send Message!
        this.emailSender.send(message);
		return new ResponseEntity<>(code, HttpStatus.OK);
	}
	
	@PutMapping("api/account/changepass/{email}")
	public ResponseEntity<Object> ChangePass(@PathVariable("email") String email, @RequestBody ChangePassword pass) {
		Account acc = service.changePass(email, pass);
		if(acc == null) return new ResponseEntity<>(acc, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}
	

}
