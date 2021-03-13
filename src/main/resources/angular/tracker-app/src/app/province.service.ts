import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {catchError, retry, tap} from 'rxjs/operators';
import { Province } from './province';

@Injectable({
  providedIn: 'root'
})
export class ProvinceService {

  private provincesUrl = 'api/provinces';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getProvinces(): Observable<Province[]> {
    return this.http.get<Province[]>(this.provincesUrl)
      .pipe(
        tap(_ => console.log('fetched provinces')),
        catchError(this.handleError<Province[]>('getProvinces', []))
      );
  }

  getProvince(id: number): Observable<Province> {
    const url = `${this.provincesUrl}/${id}`;
    return this.http.get<Province>(url).pipe(
      tap(_ => console.log(`fetched province id=${id}`)),
      catchError(this.handleError<Province>(`province id=${id}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
