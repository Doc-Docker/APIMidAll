import { Component, OnInit } from '@angular/core';
import { Product } from '../Product';
import { ProductsService } from '../../products.service'
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

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
    let params : Observable<any> =  this.activatedRoute.params;
    params.subscribe(urlParams=>{
      this.id = urlParams['id'];
      if(this.id){
        this.service
        .getProductById(this.id)
        .subscribe(
          response => this.product = response,
          errorResponse => this.product = new Product()
          )
      }
        

    })
  }

  onSubmit(){

    if(this.id){
      this.service.update(this.id, this.product)
      .subscribe( res => {
        this.success = true;
        this.errors = null;
      }
      )
    }
    else{

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
}
