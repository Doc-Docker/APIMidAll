import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/cart.service';
import { ProductsService } from 'src/app/products.service';
import { Product } from 'src/app/products/Product';
import { Cart } from '../Cart';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cart-items',
  templateUrl: './cart-items.component.html',
  styleUrls: ['./cart-items.component.css']
})
export class CartItemsComponent implements OnInit {

  cart : Cart;
  finalPrice : number = 0;
  products : Product[] = [];
  product : Product;
  promotion : number;
  discount : number;
  json: JSON;
  constructor(private service : CartService) { }

  ngOnInit(): void {
    this.products = [];
    this.finalPrice = 0;
    this.product;
    
    Cart.products.forEach(element => {
      this.product= element;
      this.products.push(element);

     
      
      this.service.getDiscount(this.json).subscribe(
        response => console.log(response),
        errorResponse => console.log(errorResponse)
        ) 
      
      this.finalPrice = this.finalPrice += (element.price  * element.quantidade);
    });

    console.log('' + this.discount)
  }

  deleteProduct(product : Product){
    

    let position = Cart.products.indexOf(product);
    if (position !== -1) {
      Cart.products.splice(position, 1);
  }    
    this.ngOnInit();
  }
}
