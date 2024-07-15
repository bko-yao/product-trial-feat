import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Product} from './product.class';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private static productslist: Product[] = null;
  private products$: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);

  constructor(private http: HttpClient) {
  }

  /**
   *
   */
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(environment.apiUrl + '/products');
  }

  /**
   *
   */
  getProduct(idProduct: number): Observable<Product> {
    return this.http.get<Product>(environment.apiUrl + '/products/' + idProduct);
  }


  /**
   *
   * @param products
   */
  create(products: Product): Observable<Product> {
    return this.http.post<Product>(environment.apiUrl + '/products', products);
  }

  update(idProduct: number, product: Product): Observable<Product> {
    return this.http.patch<Product>(environment.apiUrl + '/products/' + idProduct, product);
  }

  /**
   *
   * @param idProduct
   */
  delete(idProduct: number): Observable<any> {
    return this.http.delete(environment.apiUrl + '/products/' + idProduct);
  }
}
