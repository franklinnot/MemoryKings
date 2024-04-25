/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.sql.Date;
/**
 *
 * @author bruce
 */
public class Producto{
    
    int idProducto,idCategoria,idProveedor;
    String nombre_producto,descripcion_producto,codigo_producto,marca_producto;
    double precio_producto;
    Date fecha_creacion;
    byte[] imagen_producto;
    
    public Producto(){
        
    }
    public Producto(int idProducto,int idCategoria,int idProveedor,String nombre_producto,String descripcion_producto,String codigo_producto,String marca_producto,double precio_producto,Date fecha_creacion,byte[] imagen_producto){
        this.idProducto=idProducto;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.nombre_producto=nombre_producto;
        this.descripcion_producto=descripcion_producto;
        this.codigo_producto=codigo_producto;
        this.marca_producto = marca_producto;
        this.fecha_creacion = fecha_creacion;
        this.imagen_producto= imagen_producto;
        this.precio_producto=precio_producto;
    }
    
    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    public int getIdCategoria(){
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }
    
    public int getIdProveedor(){
        return idProveedor;
    }
    public void setIdProveedor(int idProveedor){
        this.idProveedor= idProveedor;
    }
    
    public String getnombre_Producto(){
        return nombre_producto;
    }
    public void setIdProducto(String nombre_producto){
        this.nombre_producto = nombre_producto;
    }
    
    public String getdescripcion_Producto(){
        return descripcion_producto;
    }
    public void setdescripcion_Producto(String descripcion_producto){
        this.descripcion_producto = descripcion_producto;
    }
    
    public String getcodigo_Producto(){
        return codigo_producto;
    }
    public void setcodigo_Producto(String codigo_producto){
        this.codigo_producto = codigo_producto;
    }
    
    public String getMarca_Producto(){
        return marca_producto;
    }
    public void setMarca_Producto(String marca_producto){
        this.marca_producto = marca_producto;
    }
    
    public double getPrecio_Producto(){
        return precio_producto;
    }
    public void setPrecio_Producto(double precio_producto){
        this.precio_producto = precio_producto;
    }
    
    public Date getFecha_Creacion_Producto(){
        return fecha_creacion;
    }
    public void setFecha_Creacion_Producto(Date fecha_creacion){
        this.fecha_creacion = fecha_creacion;
    }
    public byte[] getImage_Producto(){
        return imagen_producto;
    }
    public void setImagen_Producto(byte[] imagen_producto){
        this.imagen_producto = imagen_producto;
    }
    
            
    
    
    
}
