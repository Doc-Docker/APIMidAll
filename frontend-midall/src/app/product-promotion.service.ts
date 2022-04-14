import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { ProductPromotion } from './promotions/ProductPromotion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductPromotionService {

  constructor(private http : HttpClient) { }

  insert( productPromotion : ProductPromotion) : Observable<ProductPromotion>{
    let obj = {
      "discount" : productPromotion.discount,
      "products" : [
          {
              "id": productPromotion.product
          }
      ]
  
  }
    return this.http.post<ProductPromotion>('http://localhost:8080/products', obj)

  }
}
