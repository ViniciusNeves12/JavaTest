package com.javatest.apijavatest.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "tb_consulta")
public class Consulta {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotNull(message = "O peso é obrigatório")
		private float peso;
		
		@Schema(example = "00000-000")
		@NotNull(message = "O atributo cepOrigem é Obrigatório!")
		private String cepOrigem;
		
		
		@Schema(example = "00000-000")
		@NotNull(message = "O atributo cepDestino é Obrigatório!")
		private String cepDestino;
		
		@NotNull(message = "O atributo nomeDestinatario é Obrigatório!")
		private String nomeDestinatario;
		
		@NotNull(message = "O atributo vlTotalFrete é Obrigatório!")
		private float vlTotalFrete;
		
		private String dataPrevistaEntrega;
		
		@UpdateTimestamp
		private LocalDateTime dataConsulta;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public float getPeso() {
			return peso;
		}

		public void setPeso(float peso) {
			this.peso = peso;
		}

		public String getCepOrigem() {
			return cepOrigem;
		}

		public void setCepOrigem(String cepOrigem) {
			this.cepOrigem = cepOrigem;
		}

		public String getCepDestino() {
			return cepDestino;
		}

		public void setCepDestino(String cepDestino) {
			this.cepDestino = cepDestino;
		}

		public String getNomeDestinatario() {
			return nomeDestinatario;
		}

		public void setNomeDestinatario(String nomeDestinatario) {
			this.nomeDestinatario = nomeDestinatario;
		}

		public float getVlTotalFrete() {
			return vlTotalFrete;
		}

		public void setVlTotalFrete(float vlTotalFrete) {
			this.vlTotalFrete = vlTotalFrete;
		}

		public String getDataPrevistaEntrega() {
			return dataPrevistaEntrega;
		}

		public void setDataPrevistaEntrega(String dataPrevistaEntrega) {
			this.dataPrevistaEntrega = dataPrevistaEntrega;
		}

		public LocalDateTime getDataConsulta() {
			return dataConsulta;
		}

		public void setDataConsulta(LocalDateTime dataConsulta) {
			this.dataConsulta = dataConsulta;
		}
		
}
