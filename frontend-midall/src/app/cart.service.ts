import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Product } from './products/Product';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})

export class CartService {


  
  
  constructor(private http : HttpClient) { }
 
  getDiscount(id: number, quantidade: number, total:number) : Observable<any>{
    return this.http.post(`http://localhost:8080/product-promotions/discount`, {
      id: id,
      quantidade: quantidade,
      total: total
    })
  
  }

}
