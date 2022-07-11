package com.javatest.apijavatest.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatest.apijavatest.model.Consulta;
import com.javatest.apijavatest.repository.ConsultaRepository;
import com.javatest.apijavatest.service.ConsultaService;
import com.javatest.apijavatest.service.RetornoApiDTO;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ConsultaController {
	
	
	
	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	public ResponseEntity<Consulta> postPostagem(@Valid @RequestBody Consulta consulta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.realizarCalculo(consulta));
	}
}
