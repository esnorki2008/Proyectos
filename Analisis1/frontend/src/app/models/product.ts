export interface Product {
    id?:number;
    nombre?:string;
    precio?:number;
    imagen?:any;
    cantidad?:number;
    category?:string;
    descripcion?:string;
    idTienda?:number;
    idCategoria?:number;
}

export interface itemCarrito {
    CantidadCompra?: number;
    cantidadInventario?: number;
    descripcion?: string;
    id?: number;
    imagen?: string;
    nombre?: string;
    nombreCategoria?: string;
    nombreTienda?: string;
    precio?: number;
    cantidad?:number;
}

export interface ventaCarrito {
    idUsuario?: number;
    productos?: itemCarrito[];
    total?: number;
    direccion?: string;
}

export interface Product_Simple {
    nombre?:string;
    precio?:number;
    imagen?:any;
    cantidad?:number;
    category?:string;
    descripcion?:string;
    idTienda?:number;
    idCategoria?:number;
}

export interface Id_Product {
    id:number;
}
export interface Picture {
    fotografia: any;
}