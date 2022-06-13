import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { ProductPromotion } from './promotions/ProductPromotion';
import { Observable } from 'rxjs';
import { Product } from './products/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductPromotionService {

  constructor(private http : HttpClient) { }

  insert( productPromotion : ProductPromotion) : Observable<ProductPromotion>{
    return this.http.post<ProductPromotion>('http://localhost:8080/product-promotions', productPromotion)
  }

  update(id : number, productPromotion : ProductPromotion) : Observable<ProductPromotion>{
<<<<<<< Updated upstream
    let obj = {
      "id" : productPromotion.id,
      "name" : productPromotion.name,
      "isActive" : true,
      "typePromotion" : productPromotion.typePromotion,
      "receivePromotion" : productPromotion.receivePromotion,
      "quantidade" : productPromotion.quantidade,
      "discount" : productPromotion.discount,
      "products" : [
          {
              "id" : productPromotion.product
          }
        ]
    }

    return this.http.put<ProductPromotion>(`http://localhost:8080/product-promotions/${id}`, obj)

=======
    return this.http.put<ProductPromotion>(`http://localhost:8080/product-promotions/${id}`, productPromotion)
>>>>>>> Stashed changes
  }

  getAll() : Observable<ProductPromotion[]> {
    return this.http.get<ProductPromotion[]>('http://localhost:8080/product-promotions/list')
  }

  getPromotionById(id:number) : Observable<ProductPromotion>{

    return this.http.get<ProductPromotion>(`http://localhost:8080/product-promotions/${id}`)

  }

  delete(productPromotion : ProductPromotion) : Observable<any>{
    return this.http.delete<any>(`http://localhost:8080/product-promotions/${productPromotion.id}`)
  }

}
