package br.tonini.tasklist.app.rest.v1.task;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonProperty(value = "title")
	//utilizamos a propriedade notBlank para efetuar validacoes de campos obrigatorios ja na requisicao do servico.
	@NotBlank(message = "{NotBlank.task.title}", groups = { Task.AddTaskStep.class })
	private String title;

	@JsonProperty(value = "status")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean status = false;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "creationDate")
	private Date creationDate;

	@JsonProperty(value = "lastUpdateDate")
	private Date lastUpdateDate;

	@JsonProperty(value = "exclusionDate")
	private Date exclusionDate;

	@JsonProperty(value = "conclusionDate")
	private Date conclusionDate;

	public interface AddTaskStep {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	public Date getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Date conclusionDate) {
		this.conclusionDate = conclusionDate;
	};
	
}
