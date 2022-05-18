import { Component, OnInit } from '@angular/core';
import { Product } from '../Product';
import { ProductsService } from '../../products.service'
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css']
})
export class ProductsFormComponent implements OnInit {

  product : Product;
  success: boolean = false;
  errors: String[];
  id : number;

  constructor(
    private service : ProductsService,
    private activatedRoute : ActivatedRoute
    ) {
    this.product = new Product();
   }

  ngOnInit(): void {
    let params = this.activatedRoute.params
    if(params && params.value && params.value.id){
      this.id = params.value.id
      this.service
        .getProductById(this.id)
        .subscribe(
          res => this.product = res,
          errorRes => this.product = new Product()
        )
    }
  }

  onSubmit(){
    this.service
    .insert(this.product)
    .subscribe( res =>{
      this.success = true;
      this.errors = null;
      
    }, errorRes =>{
      this.success = false;
      this.errors = errorRes.error.errors
      
    }
    
    )
  }
}
