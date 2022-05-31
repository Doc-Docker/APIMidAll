import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PromotionsFormComponent } from './promotions-form/promotions-form.component'
import { PromotionsListComponent } from './promotions-list/promotions-list.component';


const routes: Routes = [
  { path : 'promotions-form', component : PromotionsFormComponent },
  { path : 'promotions-list', component : PromotionsListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PromotionsRoutingModule { }
