import { Component, OnInit } from '@angular/core';
import { ReportService } from 'src/app/services/report/report.service';
import { ToastrService } from 'ngx-toastr';
import { UserSessionService } from 'src/app/services/userSession/user-session.service';
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  historial: any = []
  periodo: string = ""
  year: number = 0;
  month: number = 0;
  selectYear: string = ""
  selectMonth: string = ""
  monthArray:string[] = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
    "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]
  yearArray:string[] = []
  constructor(private repo: ReportService, private toad: ToastrService,private sessionService : UserSessionService) { }

  ngOnInit(): void {
    this.sessionService.userHref(1);
    for(let i =2018;i<2023;i++){
      this.yearArray.push(i.toString())
    }
  }
  reportMonth() {
    this.periodo = "Mes"
    this.parseDataYear(this.selectYear)
    this.parseDataMonth(this.selectMonth)
    this.repo.historialMensual(this.year, this.month).subscribe(res => {
      this.historial = res
    }, err => {
      this.toad.error("No se pudo conseguir reportes por Mes", "Error")
    })
  }
  reportYear() {
    this.periodo = "Año"
    this.parseDataYear(this.selectYear)  
    this.repo.historialAnual(this.year).subscribe(res => {
      this.historial = res
    }, err => {
      this.toad.error("No se pudo conseguir reportes por Mes", "Annio")
    })
  }

  parseDataYear(yearStr:string){
    this.year = 0
    if(yearStr == ""){
      this.toad.error("No Hay Annio Seleccionado", "Error")
      return;
    }
    this.year = parseInt(yearStr);
  }

  parseDataMonth(monthStr:string){
    this.month = 0
    if(monthStr == ""){
      this.toad.error("No Hay Mes Seleccionado", "Error")
      return;
    }
    let comparacion = false;
    this.monthArray.forEach(element =>{
      if(comparacion == false){
        comparacion =element == monthStr
        this.month = this.month + 1 
      }
    })
    console.log(this.month)
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
