import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsFormComponent } from './products-form/products-form.component';


@NgModule({
  declarations: [
    ProductsFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProductsRoutingModule
  ], exports: [
    ProductsFormComponent
  ]
})
export class ProductsModule { }
