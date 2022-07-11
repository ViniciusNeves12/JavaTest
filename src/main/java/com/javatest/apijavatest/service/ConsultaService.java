package com.javatest.apijavatest.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javatest.apijavatest.model.Consulta;
import com.javatest.apijavatest.repository.ConsultaRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public Consulta realizarCalculo(Consulta consulta) {
		
		RetornoApiDTO cepOrigemDTO = consultarApi(consulta.getCepOrigem());
		RetornoApiDTO cepDestinoDTO = consultarApi(consulta.getCepDestino());
		
		System.out.println(cepOrigemDTO.getUf() +"     "+ cepDestinoDTO.getUf());
		System.out.println(cepOrigemDTO.getUf() +"     "+ cepDestinoDTO.getUf());

		
		float vlTotalFrete = calculaDesconto(consulta.getPeso(),cepOrigemDTO, cepDestinoDTO );
		String dataPrevista = verificaData(cepOrigemDTO, cepDestinoDTO );
		
		consulta.setPeso(consulta.getPeso());
		consulta.setCepOrigem(consulta.getCepOrigem());
		consulta.setCepDestino(consulta.getCepDestino());
		consulta.setNomeDestinatario(consulta.getNomeDestinatario());
		consulta.setVlTotalFrete(vlTotalFrete);
		consulta.setDataPrevistaEntrega(dataPrevista);
		
		return consultaRepository.save(consulta);       
    }
	
	public RetornoApiDTO consultarApi(String cep) {
		String URI = "https://viacep.com.br/ws/"+cep+"/json/";
        RestTemplate rest = new RestTemplate();
        ResponseEntity<RetornoApiDTO> retornoAPI = rest.getForEntity(URI, RetornoApiDTO.class);
        
        
        return retornoAPI.getBody();
    }
	
	public float calculaDesconto(float peso, RetornoApiDTO cepOrigemDTO, RetornoApiDTO cepDestinoDTO) {
		float desconto = 0;
		
		
		
		if(cepOrigemDTO.getDdd().equals(cepDestinoDTO.getDdd())) {
			desconto = (float) (peso * 0.5);
			
		}else if(cepOrigemDTO.getUf().equals( cepDestinoDTO.getUf())) {
			desconto = (float) (peso * 0.75);
		}else {
			desconto = peso;
		}
		

        return desconto;
    }
	
	public String verificaData(RetornoApiDTO cepOrigemDTO, RetornoApiDTO cepDestinoDTO) {
		String dataPrevista;
		Calendar c = Calendar.getInstance();
		
		
		if(cepOrigemDTO.getDdd().equals(cepDestinoDTO.getDdd())) {
			
			int dia = c.get(Calendar.DAY_OF_MONTH);
			int mes = c.get(Calendar.MONTH);
			int ano = c.get(Calendar.YEAR);
			mes++;
			dia = dia +1;
			
			dataPrevista = dia + "/" + mes + "/" + ano;
		}else if(cepOrigemDTO.getUf().equals( cepDestinoDTO.getUf())) {
			
			int dia = c.get(Calendar.DAY_OF_MONTH);
			int mes = c.get(Calendar.MONTH);
			int ano = c.get(Calendar.YEAR);
			mes++;
			dia = dia +3;
			
			dataPrevista = dia + "/" + mes + "/" + ano;
		}else {
			
			int dia = c.get(Calendar.DAY_OF_MONTH);
			int mes = c.get(Calendar.MONTH);
			int ano = c.get(Calendar.YEAR);
			mes++;
			dia = dia +10;
			
			dataPrevista = dia + "/" + mes + "/" + ano;
		}
		
        return dataPrevista;
    }
}
