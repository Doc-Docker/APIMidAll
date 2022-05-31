import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

import { PromotionsRoutingModule } from './promotions-routing.module';
import { PromotionsFormComponent } from './promotions-form/promotions-form.component';
import { PromotionsListComponent } from './promotions-list/promotions-list.component';


@NgModule({
  declarations: [PromotionsFormComponent, PromotionsListComponent],
  imports: [
    CommonModule,
    FormsModule,
    PromotionsRoutingModule
  ], exports: [
    PromotionsFormComponent
  ]
})
export class PromotionsModule { }
