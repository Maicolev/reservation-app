import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { ReservationComponent } from "./components/reservation/reservation.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CustomerComponent, ReservationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'riservifront';
}
