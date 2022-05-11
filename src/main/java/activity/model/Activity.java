package activity.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="activity")
public class Activity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="act_id")
	private Long id;
	
	@Column(name="act_description")
	private String description;
	
	@Column(name="act_done")
	private Boolean done = false;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH/mm") //H maiúsculo 15h e h minúsculo 3h
	@Column(name="act_created_date")
	private LocalDateTime createdDate; //= LocalDateTime.now();
	
	@Column(name="act_done_data")
	private LocalDateTime doneDate;
	
	@PrePersist //annotation do JPA para que esse método seja executado antes de salvar uma activity no banco
	public void beforeSave() {
		setCreatedDate(LocalDateTime.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(LocalDateTime doneDate) {
		this.doneDate = doneDate;
	}
	
	
	
	
	

}
