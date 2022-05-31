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

  ngOnInit(): void {
    this.service
      .getAll()
      .subscribe( res => this.promotions = res )
  }

}
