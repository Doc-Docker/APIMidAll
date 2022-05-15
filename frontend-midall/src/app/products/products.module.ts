import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsFormComponent } from './products-form/products-form.component';
import { ProductsListComponent } from './products-list/products-list.component';


@NgModule({
  declarations: [
    ProductsFormComponent,
    ProductsListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProductsRoutingModule
  ], exports: [
    ProductsFormComponent,
    ProductsListComponent
  ]
})
export class ProductsModule { }
