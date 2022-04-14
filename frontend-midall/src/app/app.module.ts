import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component'
import { ProductsModule } from './products/products.module';
import { ProductsService } from './products.service';
import { HttpClientModule } from '@angular/common/http'
import { PromotionsModule } from './promotions/promotions.module';
import { ProductPromotionService } from './product-promotion.service'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    ProductsModule,
    PromotionsModule
  ],
  providers: [
    ProductsService,
    ProductPromotionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
