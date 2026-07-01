package com.nidia.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity //Es un objeto de mi base de datos
@Table(name="usuarios") //Nombre de la tabla
public class Usuario {
	
	@Id //Primary Key o Clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) //AI: Autoincremento
	private Long id;
	
	@NotBlank(message="Por favor proporciona tu nombre") //No puede ser vacío
	@Size(min=3, message="El nombre debe tener al menos 3 caracteres")
	private String nombre;
	
	@NotBlank(message="Por favor proporciona tu apellido") //No puede ser vacío
	@Size(min=3, message="El apellido debe tener al menos 3 caracteres")
	private String apellido;
	
	@NotBlank(message="Por favor ingresa un correo válido")
	@Email(message="Correo inválido") //Valida que el correo este bien estructurado
	private String correo;
	
	@NotBlank(message="La contraseña es obligatoria")
	@Size(min=8, message="La contraseña necesita tener al menos 8 caracteres")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "El password necesita incluir al menos una letra mayúscula, una letra minúscula y un número")
	private String password;
	
	@Transient
	@NotBlank(message = "Debes confirmar la contraseña")
	private String confirmarPassword;

	@Column(updatable=false) // Este atributo no es puede actualizar
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private List<Postre> postres;
	
	
	//Constructor vacío
	public Usuario() {}

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmarPassword() {
		return confirmarPassword;
	}

	public void setConfirmarPassword(String confirmarPassword) {
		this.confirmarPassword = confirmarPassword;
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
	

	public List<Postre> getPostres() {
		return postres;
	}

	public void setPostres(List<Postre> postres) {
		this.postres = postres;
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