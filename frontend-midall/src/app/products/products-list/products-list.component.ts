import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/products.service';
import { Product } from '../Product';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  products : Product[] = []

  constructor(private service: ProductsService) { }

  ngOnInit(): void {
    this.service
      .getProducts()
      .subscribe( res => this.products = res )
  }

}
