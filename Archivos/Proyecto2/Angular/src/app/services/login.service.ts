import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  Api_URI='http://localhost:3000/api/'
  constructor(private http:HttpClient) { }

  verificarLogin(){
    return this.http.get('${this.Api_URI}login');
  }
}
