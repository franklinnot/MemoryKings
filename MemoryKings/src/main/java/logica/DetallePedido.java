
package logica;


public class DetallePedido {
    
    int idDetalle_Pedido,idProducto,idPedido,cantidad_DP;
    
    public DetallePedido(){
    }
    
    public DetallePedido(int idDetalle_Pedido,int idProducto,int idPedido,int cantidad_DP){
    
       this.idDetalle_Pedido = idDetalle_Pedido;
       this.idPedido=idPedido;
       this.idProducto=idProducto;
       this.cantidad_DP=cantidad_DP;
    }
    
    public int getIdDetalle_Pedido(){
        return idDetalle_Pedido;
    }
    public void setIdDetalle_Pedido(int idDetalle_Pedido){
        this.idDetalle_Pedido = idDetalle_Pedido;
    }
    
    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }
    
    public int getIdPedido(){
        return idPedido;
    }
    public void setIdPedido(int idPedido){
        this.idPedido = idPedido;
    }
    
    public int getCantidad_DP(){
        return cantidad_DP;
    }
    public void setCantidad_DP(int cantidad_DP){
        this.cantidad_DP = cantidad_DP;
    }
}
