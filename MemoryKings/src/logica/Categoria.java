/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author bruce
 */
public class Categoria {
    
    int idCategoria;
    String nombre_Categoria,descripcion_Categoria;
    
    public Categoria(){}
    
    public Categoria(int idCategoria,String nombre_Categoria,String descripcion_Categoria){
      this.descripcion_Categoria= descripcion_Categoria;
      this.idCategoria=idCategoria;
      this.nombre_Categoria=nombre_Categoria;
    }
    
    
    public int getIdCategoria(){
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }
    
    public String getDescripcion_Categoria(){
        return descripcion_Categoria;
    }
    public void setDescripcion_Categoria(String descripcion_Categoria){
        this.descripcion_Categoria = descripcion_Categoria;
    }
    
    public String getNombre_Categoria(){
        return nombre_Categoria;
    }
    public void setNombre_Categoria(String nombre_Categoria){
        this.nombre_Categoria = nombre_Categoria;
    }
}
