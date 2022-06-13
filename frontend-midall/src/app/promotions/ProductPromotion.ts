import { Product } from "../products/Product";

export class ProductPromotion{
  [x: string]: any;
  id : number;
  name : string;
  isActive : boolean = true;
  idCategory : number;
  typePromotion : string;
  receivePromotion: string;
  quantidade : number;
  discount : number;
  product : Product [] = [];
  productid : number;
  totalCompra: number;
}
