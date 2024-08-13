package com.api.facu.api_rest_facu.Models.entity;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

   @Id
   @Column(name ="id_cliente")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idCliente;

   @Column(name ="nombre")
   private String nombre;

   @Column(name ="apellido")
   private String apellido;

   @Column(name ="correo")
   private String correo;

   @Column(name ="fecha_registro")
   private Date fechaRegistro;

   public Integer getIdCliente() {
      return idCliente;
   }

   public void setIdCliente(Integer idCliente) {
      this.idCliente = idCliente;
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

   public Date getFechaRegistro() {
      return fechaRegistro;
   }

   public void setFechaRegistro(Date fechaRegistro) {
      this.fechaRegistro = fechaRegistro;
   }
}
