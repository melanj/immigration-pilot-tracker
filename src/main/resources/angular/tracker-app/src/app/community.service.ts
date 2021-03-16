import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Community} from "./community";
import {catchError, tap} from 'rxjs/operators';
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommunityService {
  private communitiesUrl = 'api/communities';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getCommunities(): Observable<Community[]> {
    return this.http.get<Community[]>(this.communitiesUrl)
      .pipe(
        tap(_ => console.log('fetched communities')),
        catchError(this.handleError<Community[]>('getCommunities', []))
      );
  }

  getCommunity(id: number): Observable<Community> {
    const url = `${this.communitiesUrl}/${id}`;
    return this.http.get<Community>(url).pipe(
      tap(_ => console.log(`fetched community id=${id}`)),
      catchError(this.handleError<Community>(`community id=${id}`))
    );
  }

  addCommunity(community: Community): Observable<Community> {
    return this.http.post<Community>(this.communitiesUrl, community, this.httpOptions)
      .pipe(
        catchError(this.handleError('addCommunity', community))
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
