import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PromotionsFormComponent } from './promotions-form/promotions-form.component'


const routes: Routes = [
  { path : 'promotions-form', component : PromotionsFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionsRoutingModule { }
