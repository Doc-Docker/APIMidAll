import { Component, OnInit } from '@angular/core';
import { ProductPromotionService } from 'src/app/product-promotion.service'
import { ProductPromotion } from '../ProductPromotion';

@Component({
  selector: 'app-promotions-list',
  templateUrl: './promotions-list.component.html',
  styleUrls: ['./promotions-list.component.css']
})
export class PromotionsListComponent implements OnInit {

  constructor(private service: ProductPromotionService) { }

  promotions : ProductPromotion[] = []
  id: number;
  selectedPromotion : ProductPromotion;
  success : string;
  failed : string;
  
  ngOnInit(): void {
    this.service
      .getAll()
      .subscribe( res => this.promotions = res )
  }

  preDelete(productPromotion : ProductPromotion){
    this.selectedPromotion = productPromotion;

  }

  deletePromotion(){
    this.service.delete(this.selectedPromotion)
    .subscribe(
      res => {this.success = 'Promotion successfully deleted',
      this.ngOnInit();
    },
      erro => this.failed = 'There was an error deleting the Promotion'
      )

  }

}
