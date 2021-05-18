package br.com.devinhouse.grupo04.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.devinhouse.grupo04.entity.Assunto;
import br.com.devinhouse.grupo04.entity.Interessado;

public class ProcessoDTO implements Serializable {
	
	private static final long serialVersionUID = 5334377842661430069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nuProcesso;
	private String sgOrgaoSetor;
	private String nuAno;
	private String chaveProcesso;
	private String descricao;
	private Assunto cdAssunto;
	private Interessado cdInteressado;
	
	public ProcessoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNuProcesso() {
		return nuProcesso;
	}

	public void setNuProcesso(Integer nuProcesso) {
		this.nuProcesso = nuProcesso;
	}

	public String getSgOrgaoSetor() {
		return sgOrgaoSetor;
	}

	public void setSgOrgaoSetor(String sgOrgaoSetor) {
		this.sgOrgaoSetor = sgOrgaoSetor;
	}

	public String getNuAno() {
		return nuAno;
	}

	public void setNuAno(String nuAno) {
		this.nuAno = nuAno;
	}

	public String getChaveProcesso() {
		return chaveProcesso;
	}

	public void setChaveProcesso(String chaveProcesso) {
		this.chaveProcesso = chaveProcesso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Assunto getCdAssunto() {
		return cdAssunto;
	}

	public void setCdAssunto(Assunto cdAssunto) {
		this.cdAssunto = cdAssunto;
	}

	public Interessado getCdInteressado() {
		return cdInteressado;
	}

	public void setCdInteressado(Interessado cdInteressado) {
		this.cdInteressado = cdInteressado;
	}
	
}
