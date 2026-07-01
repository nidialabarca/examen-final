package com.nidia.modelos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "postres")
public class Postre {

	@Id //Primary Key o Clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) //AI: Autoincremento
	private Long id;

	@NotBlank(message = "Por favor proporciona el nombre")
	@Size(min = 5, message = "El nombre debe tener al menos 5 caracteres")
	private String nombre;

	@NotBlank(message = "Por favor proporciona los ingredientes")
	@Size(min = 10, message = "Los ingredientes deben tener al menos 10 caracteres")
	@Column(columnDefinition="TEXT")
	private String ingredientes;

	@NotBlank(message = "Por favor proporciona las instrucciones")
	@Size(min = 10, message = "Las instrucciones deben tener al menos 10 caracteres")
	@Column(columnDefinition="TEXT")
	private String instrucciones;

	@NotBlank(message = "Por favor proporciona una URL válida con la imagen")
	private String imagen;

	@NotNull(message = "Por favor proporciona el tiempo de preparación")
	@Min(value = 1, message = "El tiempo debe ser mayor a 0")
	private Double tiempoPreparacion; //minutos
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;


	public Postre() {}

	// getters y setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getIngredientes() {
		return ingredientes;
	}


	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}


	public String getInstrucciones() {
		return instrucciones;
	}


	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public Double getTiempoPreparacion() {
		return tiempoPreparacion;
	}


	public void setTiempoPreparacion(Double tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//ANTES de crear un nuevo registro
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date(); //createdAt = CURRENT_TIMESTAMP
		this.updatedAt = this.createdAt;
	}
			
	//ANTES de que actualice un registro
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date(); //updatedAt = CURRENT_TIMESTAMP
	}
	
	
}