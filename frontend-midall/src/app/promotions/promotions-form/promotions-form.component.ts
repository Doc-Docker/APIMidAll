import { Component, OnInit } from '@angular/core';
import { ProductPromotionService } from 'src/app/product-promotion.service';
import { ProductPromotion } from '../ProductPromotion';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from 'src/app/products/Product';
@Component({
  selector: 'app-promotions-form',
  templateUrl: './promotions-form.component.html',
  styleUrls: ['./promotions-form.component.css']
})
export class PromotionsFormComponent implements OnInit {

  productPromotion : ProductPromotion
  success: boolean = false;
  errors: String[];
  id : number;
  lista_promotion : String[] = ['PRODUCT','TOTAL','PRODUCT_QUANTITY','CATEGORY'];
  lista_type: String[] = ['VALUE', 'PERCENTAGE'];


  constructor(
    private service : ProductPromotionService,
    private activatedRoute : ActivatedRoute
    ) {
    this.productPromotion = new ProductPromotion();
   }

  ngOnInit(): void {
    let params : Observable<any> =  this.activatedRoute.params;
    params.subscribe(urlParams=>{
      this.id = urlParams['id'];
      if(this.id){
        this.service
        .getPromotionById(this.id)
        .subscribe(
          response => this.productPromotion = response,
          errorResponse => this.productPromotion = new ProductPromotion()
          )
      }


    })
  }

  onSubmit(){
    if(this.id){
      this.service.update(this.id, this.productPromotion)
      .subscribe( res => {
        this.success = true;
        this.errors = null;
      }
      )
    }
    else{

      this.service
        .insert(this.productPromotion)
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
