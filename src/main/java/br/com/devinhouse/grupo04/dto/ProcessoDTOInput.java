package br.com.devinhouse.grupo04.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ProcessoDTOInput implements Serializable {

	private static final long serialVersionUID = -393417692491112721L;

	@NotNull
	private Integer nuProcesso;
	@NotNull
	private String sgOrgaoSetor;
	@NotNull
	private String nuAno;
	@NotNull
	private String chaveProcesso;
	@NotNull
	private String descricao;

	@NotNull
	private Long cdAssuntoId;

	@NotNull
	private Long cdInteressadoId;

	public ProcessoDTOInput() {
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

	public Long getCdAssuntoId() {
		return cdAssuntoId;
	}

	public void setCdAssuntoId(Long cdAssuntoId) {
		this.cdAssuntoId = cdAssuntoId;
	}

	public Long getCdInteressadoId() {
		return cdInteressadoId;
	}

	public void setCdInteressadoId(Long cdInteressadoId) {
		this.cdInteressadoId = cdInteressadoId;
	}

}