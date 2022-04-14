import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

import { PromotionsRoutingModule } from './promotions-routing.module';
import { PromotionsFormComponent } from './promotions-form/promotions-form.component';


@NgModule({
  declarations: [PromotionsFormComponent],
  imports: [
    CommonModule,
    FormsModule,
    PromotionsRoutingModule
  ], exports: [
    PromotionsFormComponent
  ]
})
export class PromotionsModule { }
