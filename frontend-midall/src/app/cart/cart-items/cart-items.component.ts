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
  promotion : number;
  discount : Observable<number>;
  
  constructor(private service : CartService) { }

  ngOnInit(): void {
    this.products = [];
    this.finalPrice = 0;
    Cart.products.forEach(element => {
      
      this.products.push(element);

      this.service.getDiscount(this.products).subscribe(
        response => console.log(response),
        errorResponse => console.log(errorResponse)
        ) 

      this.finalPrice = this.finalPrice += (element.price * element.quantidade);//teste José
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
