import { Component, OnInit } from '@angular/core';
import { Product } from '../Product';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css']
})
export class ProductsFormComponent implements OnInit {

  product : Product;

  constructor() {
    this.product = new Product();
   }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.product)
  }
}