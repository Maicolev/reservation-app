import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
//import { environment } from 'src/environments/environment';
import { ReservationRequest } from '../models/reservation-request.model';
import { ReservationResponse } from '../models/reservation-response.model';
import { Reservation } from '../models/reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiUrl = 'http://localhost:8080/api/reservations';

  constructor(private http: HttpClient) { }

  // Crear una reservación
  createReservation(request: ReservationRequest): Observable<ReservationResponse> {
    return this.http.post<ReservationResponse>(this.apiUrl, request)
      .pipe(catchError(this.handleError));
  }

  // Obtener todas las reservaciones
  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  // Actualizar una reservación
  updateReservation(id: number, request: ReservationRequest): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/${id}`, request)
      .pipe(catchError(this.handleError));
  }

  // Eliminar una reservación
  deleteReservation(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  // Manejo de errores
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}