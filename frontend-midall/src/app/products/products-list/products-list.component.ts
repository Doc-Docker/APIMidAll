import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/cart/Cart';
import { ProductsService } from 'src/app/products.service';
import { Product } from '../Product';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  products : Product[] = []
  id: number;
  lista : number[] = [1,2,3,4,5];
  selectedProduct : Product;
  success : string;
  failed : string;

  constructor(private service: ProductsService) { }

  ngOnInit(): void {
    this.service
      .getProducts()
      .subscribe( res => this.products = res )
  }

  addProduct(product : Product){

    if(product.categories != null){ //teste José .quantidade
      Cart.products.push(product);
    }
    
    this.ngOnInit();
  }

  preDelete(product : Product){
    this.selectedProduct = product;

  }

  deleteProduct(){
    this.service.delete(this.selectedProduct)
    .subscribe(
      res => {this.success = 'Product successfully deleted',
      this.ngOnInit();
    },
      erro => this.failed = 'There was an error deleting the Product'
      )

  }

}
