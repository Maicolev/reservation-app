import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReservationService } from '../../services/reservation.service';
import { ReservationRequest } from '../../models/reservation-request.model';

@Component({
  selector: 'app-reservation',
  imports: [FormsModule],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  reservationRequest: ReservationRequest = {
    reservation: {
      id: 0,
      customer: {
        id: '6666',
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '',
        customerType: {
          id: 1,
          typeName: '',
          monthlyFee: 0,
          discountPercentage: 0
        }
      },
      schedule: {
        id: 1112202512,
        scheduleDate: toISODateString(),
        startTime: '00:00',
        endTime: '01:00',
        isAvailable: true
      },
      reservationType: {
        id: 1,
        typeName: ''
      },
      reservationDate: toISODateString(),
      numberOfPeople: 0,
      discountApplied: 0
    }
  };

  constructor(private reservationService: ReservationService) {}

  // Method to generate id
  generateScheduleId() {
    const startTime = this.reservationRequest.reservation.schedule.startTime;
    const scheduleDate = new Date(this.reservationRequest.reservation.schedule.scheduleDate);

    const day = scheduleDate.getDate().toString().padStart(2, '0');
    const month = (scheduleDate.getMonth() + 1).toString().padStart(2, '0');
    const year = scheduleDate.getFullYear();

    const hours = startTime.split(':')[0];

    // Format ddMMyyyyHH
    return `${day}${month}${year}${hours}`;
  }

  createReservation() {
    //const scheduleDate = new Date(this.reservationRequest.reservation.schedule.scheduleDate);
    //const startTimeParts = this.reservationRequest.reservation.schedule.startTime.split(':');
    //const startDateTime = new Date(scheduleDate.setHours(parseInt(startTimeParts[0]), parseInt(startTimeParts[1]), 0, 0));

    this.reservationRequest.reservation.schedule.id = Number(this.generateScheduleId());

    console.log(this.reservationRequest);

    this.reservationService.createReservation(this.reservationRequest).subscribe({
      next: (response) => {
        console.log('Reservation created successfully', response);
      },
      error: (err) => {
        console.error('Error creating reservation', err);
      }
    });
  }
}
function toISODateString(): string {
  const date = new Date();
  return date.toISOString().split('T')[0]; // Formato 'yyyy-MM-dd'
}

