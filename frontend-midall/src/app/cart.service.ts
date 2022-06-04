import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Product } from './products/Product';
import { Observable } from 'rxjs';
import { CartItemsComponent } from './cart/cart-items/cart-items.component';
import { nullSafeIsEquivalent } from '@angular/compiler/src/output/output_ast';
import { Json } from './products/json';
import { ProductPromotionService } from './product-promotion.service';



@Injectable({
  providedIn: 'root'
})
export class CartService {
  id : number;
  quantidade : number;
  total: number;
  products: Product;
  constructor(private http : HttpClient) { }


  getDiscount(json : JSON) : Observable<any>{
    return this.http.post<any>(`http://localhost:8080/product-promotions/discount`, json)
    
  }

}
