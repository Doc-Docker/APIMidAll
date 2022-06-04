import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Product } from './products/Product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http : HttpClient) { }

  getDiscount(products : Product[]) : Observable<number>{
    return this.http.post<number>(`http://localhost:8080/product-promotions/discount`, products)
  }

}
