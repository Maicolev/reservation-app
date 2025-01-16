import { Customer } from './customer.model';  // Asegúrate de tener un modelo Customer
import { Schedule } from './schedule.model';  // Asegúrate de tener un modelo Schedule
import { ReservationType } from './reservation-type.model';  // Asegúrate de tener un modelo ReservationType

export interface Reservation {
  id: number;
  customer: Customer;
  schedule: Schedule;
  reservationType: ReservationType;
  reservationDate: string;
  numberOfPeople: number;
  discountApplied: number;
}