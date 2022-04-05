import { Component, OnInit } from '@angular/core';
import { Product } from '../Product';
import { ProductsService } from '../../products.service'

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css']
})
export class ProductsFormComponent implements OnInit {

  product : Product;

  constructor(private service : ProductsService) {
    this.product = new Product();
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.service
    .insert(this.product)
    .subscribe( res =>{
      console.log(res)
    })
  }
}
