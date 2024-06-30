package com.example.demo.repositories;

import com.example.demo.models.UsuariosModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuariosModel, Long> {

 @Query(value ="SELECT * FROM u255965900_cedemusadb.usuarios u where u.nombre like %:nombre%",nativeQuery = true)
 List<UsuariosModel> buscarNombre(String nombre);

 @Query(value ="SELECT * FROM u255965900_cedemusadb.usuarios u where u.matricula = ?1",nativeQuery = true)
 List<UsuariosModel>  buscarMatricula(String matricula);

 @Query(value ="SELECT * FROM u255965900_cedemusadb.usuarios u where u.id_usuario = ?1",nativeQuery = true)
 List<UsuariosModel>  buscarID(String idUsuario);

 //public abstract ArrayList<UsuariosModel> findByNombre(String nombre);
 // Query Nativo (como se ejecutaría en SQL)

 @Query(value = "select * from u255965900_cedemusadb.usuario c where upper(c.nombre) like upper(concat('%', ?1,'%'))", nativeQuery = true)
 List<UsuariosModel> searchByNombreQueryNative(String nombre);
}