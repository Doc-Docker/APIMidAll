import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { CartItemsComponent } from './cart-items/cart-items.component';


@NgModule({
  declarations: [CartItemsComponent],
  imports: [
    CommonModule,
    CartRoutingModule
  ],
  exports:[
    CartItemsComponent
  ]
})
export class CartModule { }
