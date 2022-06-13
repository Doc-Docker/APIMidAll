import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ProductPromotionService } from 'src/app/product-promotion.service';
import { ProductPromotion } from '../ProductPromotion';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from 'src/app/products/Product';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-promotions-form',
  templateUrl: './promotions-form.component.html',
  styleUrls: ['./promotions-form.component.css']
})
export class PromotionsFormComponent implements OnInit {

  @Output() productsEmitter = new EventEmitter();
  productPromotion : ProductPromotion
  success: boolean = false;
  
  errors: String[];
  id : number;
  lista_promotion : String[] = ['PRODUCT','TOTAL','PRODUCT_QUANTITY'];
  lista_type: String[] = ['VALUE', 'PERCENTAGE'];
  p1: boolean = true;
  p2: boolean = true;
  p3: boolean = true;
  p4: boolean = true;
  receivePromotion : string = "teste"; 
  

    pegaValor(){ // Função que foi chamada
      this.receivePromotion = this.productPromotion.receivePromotion;
      if(this.receivePromotion == 'PRODUCT'){
        this.p1 =false;
      }
      if(this.receivePromotion == 'PRODUCT_QUANTITY'){
        this.p2 =false;
        this.p1 =false;
      }
      if(this.receivePromotion == 'TOTAL'){
<<<<<<< Updated upstream
        this.productPromotion.product=1;
=======
        //this.productPromotion.product;
>>>>>>> Stashed changes
        this.p3 =false;
      }
      // if(this.receivePromotion == 'CATEGORY'){
      //   this.productPromotion.product=1;
      //   this.p4 =false;
      // }
      
    }


  constructor(
    private service : ProductPromotionService,
    private activatedRoute : ActivatedRoute
    ) {
    this.productPromotion = new ProductPromotion();
   }

   
  ngOnInit(): void {
    let params : Observable<any> =  this.activatedRoute.params;
    params.subscribe(urlParams=>{
      this.id = urlParams['id'];
      if(this.id){
        this.service
        .getPromotionById(this.id)
        .subscribe(
          response => this.productPromotion = response,

         
          errorResponse => this.productPromotion = new ProductPromotion()
          )
      }


    })
  }


  onAdd() {
    let product : Product;
    product = new Product();
    product.id = this.productPromotion.productid;
    this.productPromotion.product.push(product)
    this.productsEmitter.emit(this.productPromotion.product)
    console.log(this.productPromotion);
  }

  onSubmit(){

    if(this.id){
      this.service.update(this.id, this.productPromotion)
      .subscribe( res => {
        this.success = true;
        this.errors = null;
      }
      )
    }
    else{

      this.service
        .insert(this.productPromotion)
        .subscribe( res =>{
          this.success = true;
          this.errors = null;

        }, errorRes =>{
          this.success = false;
          this.errors = errorRes.error.errors

        }

        )
    }
  }

}
