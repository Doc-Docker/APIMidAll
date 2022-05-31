import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/products/Product';
import { Cart } from '../Cart';

@Component({
  selector: 'app-cart-items',
  templateUrl: './cart-items.component.html',
  styleUrls: ['./cart-items.component.css']
})
export class CartItemsComponent implements OnInit {

  cart : Cart;
  finalPrice : number = 0;
  products : Product[] = [];
  promotion : number;
  
  constructor() { }

  ngOnInit(): void {
    this.products = [];
    this.finalPrice = 0;
    Cart.products.forEach(element => {
      
      this.products.push(element);
      this.finalPrice = this.finalPrice += (element.price * element.quantidade);
    });
  }

  deleteProduct(product : Product){
    

    let position = Cart.products.indexOf(product);
    if (position !== -1) {
      Cart.products.splice(position, 1);
  }    
    this.ngOnInit();
  }
}
