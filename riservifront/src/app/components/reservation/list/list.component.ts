import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../../services/reservation.service';
import { Reservation } from '../../../models/reservation.model';
import { ReservationRequest } from '../../../models/reservation-request.model'; // Modelo de la solicitud de reserva
import { CommonModule } from '@angular/common'; // Import CommonModule
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-list',
  imports:[CommonModule, FormsModule],
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  reservations: Reservation[] = [];  // Lista de reservaciones
  editMode: boolean = false;         // Modo de edición para actualizar una reservación
  reservationToEdit: Reservation | null = null; // Reservación seleccionada para editar

  constructor(private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.loadReservations();  // Cargar las reservaciones al inicializar el componente
  }

  // Cargar todas las reservaciones
  loadReservations(): void {
    this.reservationService.getAllReservations().subscribe(
      (data: Reservation[]) => {
        this.reservations = data;
      },
      (error) => {
        console.error('Error loading reservations', error);
      }
    );
  }

  // Activar modo edición para actualizar una reservación
  editReservation(reservation: Reservation): void {
    this.editMode = true;
    this.reservationToEdit = { ...reservation };
  }

  // Actualizar una reservación
  updateReservation(): void {
    if (this.reservationToEdit) {
      const request: ReservationRequest = {
        reservation: {
          customer: this.reservationToEdit.customer,
          schedule: this.reservationToEdit.schedule,
          reservationType: this.reservationToEdit.reservationType,
          reservationDate: this.reservationToEdit.reservationDate,
          numberOfPeople: this.reservationToEdit.numberOfPeople,
          discountApplied: this.reservationToEdit.discountApplied,
          id: 0
        }
      };

      this.reservationService.updateReservation(this.reservationToEdit.id, request).subscribe(
        () => {
          alert('Reservación actualizada exitosamente');
          this.editMode = false;
          this.reservationToEdit = null;
          this.loadReservations();
        },
        (error) => {
          console.error('Error updating reservation', error);
        }
      );
    }
  }

  // Eliminar una reservación
  deleteReservation(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar esta reservación?')) {
      this.reservationService.deleteReservation(id).subscribe(
        () => {
          alert('Reservación eliminada');
          this.loadReservations();
        },
        (error) => {
          console.error('Error deleting reservation', error);
        }
      );
    }
  }
}