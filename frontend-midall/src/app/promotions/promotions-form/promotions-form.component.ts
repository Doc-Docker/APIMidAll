import { Component, OnInit } from '@angular/core';
import { ProductPromotionService } from 'src/app/product-promotion.service';
import { ProductPromotion } from '../ProductPromotion';

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

  constructor(private service : ProductPromotionService) {
    this.productPromotion = new ProductPromotion();
   }

  ngOnInit(): void {
  }

  onSubmit(){
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
