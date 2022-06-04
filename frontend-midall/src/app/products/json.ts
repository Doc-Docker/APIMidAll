import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/cart.service';
import { ProductsService } from 'src/app/products.service';
import { Product } from 'src/app/products/Product';


export class Json  {
    constructor(
        public id:number,
        public quantidade: number,
        public total : number
    ){}
}