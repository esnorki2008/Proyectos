export interface Store {
    //USUARIO
    idUsuario?: number;
    nombre?:string;
    apellido?:string;
    correo?:string;
    contrasenia?:string;
    confirmarContrasenia?:string;
    telefono?:string;
    idRol?: number;
    estado?: number;
    //TIENDA
    idTienda?: number;
    nombreTienda?:string;
    slogan?:string;
    imagen?:string;
    
}   


export interface StoreSimple {
    //USUARIO
    idUsuario?: number;
    //TIENDA
    id?: number;
    nombreTienda?:string;
    slogan?:string;
    estado?: number;
    imagen?: string;
    // Extra para la response
    nombre?:string;
}   
