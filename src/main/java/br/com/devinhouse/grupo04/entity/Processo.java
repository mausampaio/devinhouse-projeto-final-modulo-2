package br.com.devinhouse.grupo04.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "processos")
@Table(
    name="PROCESSOS", 
    uniqueConstraints=
        @UniqueConstraint(columnNames={"chaveProcesso", "nuProcesso"})
)
public class Processo implements Serializable {
	
	private static final long serialVersionUID = 3446200032394564533L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nuProcesso;
	private String sgOrgaoSetor;
	private String nuAno;
	private String chaveProcesso;
	private String descricao;
	@ManyToOne
  @JoinColumn(name = "cd_assunto_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
	private Assunto cdAssunto;
	@ManyToOne
  @JoinColumn(name = "cd_interessado_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
	private Interessado cdInteressado;
	
	public Processo() {
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
