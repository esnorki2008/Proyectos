import { Component, OnInit } from '@angular/core';
import { compareByFieldSpec } from '@fullcalendar/core';
import { User, UserComplete } from 'src/app/models/user';
import { ToastrService } from 'ngx-toastr';
import { DataService } from '../../../services/data.service';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';

interface history {
  direccion?: string;
  estado?: number;
  fecha?: string;
  id?: number;
  idUsuario?: number;
  total?: number;
  nombreUsuario?: string;
}


@Component({
  selector: 'app-cprofile',
  templateUrl: './cprofile.component.html',
  styleUrls: ['./cprofile.component.css']
})
export class CprofileComponent implements OnInit {
  mostrarHistoria: boolean = false;
  inforUsuario: UserComplete = {
    id: 0,
    nombre: "",
    apellido: "",
    correo: "",
    contrasenia: "",
    telefono: 0,
    idRol: 0,
    estado: 0
  }
  credentials: User = {
    contrasenia: "",
    correo: ""
  }
  rolTexto: string = ""
  oldPassword: string = ""
  fullName: string = ""
  firstName: String = ""
  secondName: String = ""
  phone: number = 0
  tipoHistorial: string = ""
  historial: any = []
  countTransact:number = 0
  constructor(private toastr: ToastrService, private userService: DataService, private sess: UserSessionService) { }
  ngOnInit(): void {
    this.sess.userHref(5);
    this.updateModel();
    this.rolTexto = "Cliente"
        this.sess.historial(this.inforUsuario.id || 0).subscribe(res => {
          this.historial = res
          this.countTransact=this.historial.length
          console.log(res)
        }, res => {
          this.toastr.error("Error Al Obtener Historial", "Error");
        })
    switch (this.inforUsuario.idRol) {
      case 1:
        this.rolTexto = "Admin"
        break;
      case 2:
        this.tipoHistorial = "Compra"
        break;
      case 3:
        this.tipoHistorial = "Venta"
        this.rolTexto = "Administrador De Tienda"
        break;
      default:
        break;
    }
  }
  updateModel(): void {
    this.inforUsuario.nombre = localStorage.getItem('userFirstName') || "";
    this.inforUsuario.apellido = localStorage.getItem('userLastName') || "";
    this.inforUsuario.correo = localStorage.getItem('userEmail') || "";
    this.fullName = this.inforUsuario.nombre + "  " + this.inforUsuario.apellido
    this.inforUsuario.id = parseInt(localStorage.getItem('userId') || "0");
    this.inforUsuario.idRol = parseInt(localStorage.getItem('userIdRol') || "0");
    this.inforUsuario.telefono = parseInt(localStorage.getItem('userPhone') || "0");
    this.inforUsuario.estado = parseInt(localStorage.getItem('userEstado') || "0");
    this.inforUsuario.contrasenia = ""
    this.oldPassword = ""
    this.firstName = this.inforUsuario.nombre
    this.secondName = this.inforUsuario.apellido
    this.phone = this.inforUsuario.telefono
  }
  updateUser(): void {
    if (this.oldPassword == "") {
      this.toastr.error("Se Tiene Que Colocar La Contrasenia Actual Para Actualizar Datos", 'Error');
      return;
    }
    this.credentials.contrasenia = this.oldPassword
    this.credentials.correo = this.inforUsuario.correo || ""
    this.userService.postLogin(this.credentials)
      .subscribe(
        (res) => {
          if (res != undefined) {
            if (this.inforUsuario.contrasenia == "")
              this.inforUsuario.contrasenia = this.oldPassword
            this.userService.updateClient(this.inforUsuario).subscribe(
              res => {
                this.toastr.success("Actualizacion Correcta", "Aviso");
                this.sess.login(this.inforUsuario)
                this.updateModel()
              },
              err => {
                this.toastr.error("Error", "Error Al Actualizar Datos");
                this.inforUsuario.contrasenia = ""
              })

          }

          return;
        },
        err => {
          this.toastr.error("Error", "Debe Proporcionarse La Contrasenia Actual Para Actualizar La Informacion");
        }
      )
  }
  activarHistorial(): void {
    this.mostrarHistoria = true
  }
  activarDatos(): void {
    this.mostrarHistoria = false
  }
  estadoStr(input:number): string{
    switch (input) {
      case 0:
        return "Sin Aprobarse"
      case 1:
          return "Aprobada "
      case 4:
            return "Rechazada"
      default:
          return "Esperando"
    }
  }

}
