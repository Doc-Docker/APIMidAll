import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/cart.service';
import { ProductsService } from 'src/app/products.service';
import { Product } from 'src/app/products/Product';
import { Cart } from '../Cart';
import { Observable } from 'rxjs';
import { ProductDTO } from 'src/app/products/ProductDTO';

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
  discount :any = 0;
  noDiscount : number = 0;
  id : number;
  quantidade : number;
  total: number;
  categoria: number =1;
  lista: Product[] = [];

  teste : ProductDTO[] = []
  constructor(private service : CartService) { }

  ngOnInit(): void {
    this.products = [];
    this.finalPrice = 0;
    this.product;
    this.discount;
    this.categoria=0;
    this.noDiscount = 0;
    this.lista = [];
    this.teste = [];
    Cart.products.forEach(element => {
      this.product = element;
      this.id  = element.id;
      this.quantidade = element.quantidade;
      this.categoria =  element.id;
      this.products.push(element);
      
        this.total = this.noDiscount += (element.price  * element.quantidade);

        this.service.getDiscount(this.id, this.quantidade, this.total, this.categoria).subscribe(
            response =>
            { const product : Product = new Product();
              this.discount = response;
              this.product.discount = this.discount
              this.finalPrice = this.finalPrice += (element.price * element.quantidade)-(this.discount)
              console.log("teste", this.categoria)
            errorResponse => console.log(errorResponse)
        })
    });

  }

  deleteProduct(product : Product){
    let position = Cart.products.indexOf(product);
    if (position !== -1) {
      Cart.products.splice(position, 1);
  }
    this.ngOnInit();
  }

  deleteProductDto(product : ProductDTO){
    let position = this.teste.indexOf(product)
    if (position !== -1) {
      this.teste.splice	(position, 1)

    }

    this.ngOnInit();
  }
}
