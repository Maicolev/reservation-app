import { Routes } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { ListComponent } from './components/reservation/list/list.component';

export const routes: Routes = [
    {path: 'customer', component: CustomerComponent},
    {path: 'reservation', component: ReservationComponent},
    {path: 'list', component: ListComponent}
];
