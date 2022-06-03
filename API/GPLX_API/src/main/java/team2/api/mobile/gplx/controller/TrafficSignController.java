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

import team2.api.mobile.gplx.models.TrafficSign;
import team2.api.mobile.gplx.service.interfaces.TrafficSignService;

@RestController
public class TrafficSignController {

	@Autowired
	private TrafficSignService service;

	@GetMapping("api/trafficsign")
	public ResponseEntity<Object> GetAll() {
		List<TrafficSign> trafficSign = service.findAll();
		return new ResponseEntity<>(trafficSign, HttpStatus.OK);
	}

	@GetMapping("api/trafficsign/{id}")
	public ResponseEntity<Object> GetById(@PathVariable("id") String id) {
		TrafficSign trafficSign = service.findTrafficSignById(id);
		return new ResponseEntity<>(trafficSign, HttpStatus.OK);
	}

	@PostMapping("api/trafficsign/add")
	public ResponseEntity<Object> Post(@RequestBody TrafficSign trafficSign) {
		TrafficSign newTrafficSign = service.save(trafficSign);
		if (newTrafficSign == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(newTrafficSign, HttpStatus.OK);
	}

	@PutMapping("api/trafficsign/edit/{id}")
	public ResponseEntity<Object> Put(@PathVariable("id") String id, @RequestBody TrafficSign trafficSign) {
		TrafficSign updatedTrafficSign = service.update(trafficSign, id);
		if (updatedTrafficSign == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(updatedTrafficSign, HttpStatus.OK);
	}

	@DeleteMapping("api/trafficsign/delete/{id}")
	public ResponseEntity<Object> Delete(@PathVariable("id") String id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
