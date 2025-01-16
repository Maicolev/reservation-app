import { Customer } from "./customer.model";
import { Reservation } from "./reservation.model";
import { Schedule } from "./schedule.model";

export interface ReservationRequest {
    reservation: Reservation;
  }