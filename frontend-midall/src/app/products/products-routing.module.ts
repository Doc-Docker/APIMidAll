import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductsFormComponent } from './products-form/products-form.component'
import { ProductsListComponent } from './products-list/products-list.component';

const routes: Routes = [
  { path : 'products-form', component : ProductsFormComponent },
  { path : 'products-list', component : ProductsListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
