import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {Pilot} from './pilot';

@Injectable({
  providedIn: 'root'
})
export class PilotService {
  private pilotsUrl = 'api/pilots';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getPilots(): Observable<Pilot[]> {
    return this.http.get<Pilot[]>(this.pilotsUrl)
      .pipe(
        tap(_ => console.log('fetched pilots')),
        catchError(this.handleError<Pilot[]>('getPilots', []))
      );
  }

  getPilot(id: number): Observable<Pilot> {
    const url = `${this.pilotsUrl}/${id}`;
    return this.http.get<Pilot>(url).pipe(
      tap(_ => console.log(`fetched pilot id=${id}`)),
      catchError(this.handleError<Pilot>(`pilot id=${id}`))
    );
  }

  addPilot(pilot: Pilot): Observable<Pilot> {
    return this.http.post<Pilot>(this.pilotsUrl, pilot, this.httpOptions)
      .pipe(
        catchError(this.handleError('addPilot', pilot))
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
