import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../models/user';
import * as globales from '../../Global_var';
import { FormularioAprobacion } from '../models/formulario-aprobacion';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  
  constructor(private http: HttpClient) { }

  postLogin(user: User){
    return this.http.post(`${globales.api_url}/login`, user);
  }

  getStores(){
    return this.http.get(`${globales.api_url}/store/pendingStores`);
  }

  postAuthorizeStore(responseStore: FormularioAprobacion){
    return this.http.post(`${globales.api_url}/store/authorize`, responseStore)
  }

  postRejectStore(responseStore: FormularioAprobacion){
    return this.http.post(`${globales.api_url}/store/reject`, responseStore)
  }

  updateClient(client:any){
    return this.http.post(`${globales.api_url}/user/update`,client);
  }
}
