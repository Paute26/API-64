package ec.edu.ups.sd64.evarest.model;
//Atlas
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class General {
	
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String descripcion;
	private String caracteristica;
	
	//Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	@Override
	public String toString() {
		return "General [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", caracteristica="
				+ caracteristica + "]";
	}
	
	
	
	
	

}
