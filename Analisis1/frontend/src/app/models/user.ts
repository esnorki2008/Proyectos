export interface User {
    correo:string;
    contrasenia:string;
}

export interface UserComplete {
    id?:number;
    nombre?:string;
    apellido?:string;
    correo?:string;
    contrasenia?:string;
    telefono?:number;
    idRol?:number;
    estado?:number;
}