import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { RouterOutlet } from '@angular/router';
import { Customer } from '../../models/customer.model';
import { CustomerType } from '../../models/customertype.model';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-customer',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatListModule
  ],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})

export class CustomerComponent {
  customers: Customer[] = [];
  newCustomer: Customer | undefined;

  customerTypes: CustomerType[] = []; // Definir la propiedad customerTypes



  constructor(private customerService: CustomerService) {
    console.log('constructor');
    this.fetchCustomers();
    console.log(this.customers);
  }

  fetchCustomers() {
    this.customerService.getAllCustomers().subscribe({
      next: (data: Customer[]) => {
        this.customers = data;
        console.log(this.customers);
      },
      error: (err) => {
        console.error('Error fetching customers:', err);
      }
    });
  }

  ngOnInit(): void {
    // Aquí debes llenar la propiedad customerTypes, por ejemplo con un servicio que haga una petición HTTP
    this.customerTypes = [
      { id: 1, typeName: 'Regular', monthlyFee: 0, discountPercentage: 0},
      { id: 2, typeName: 'Premium', monthlyFee: 50, discountPercentage: 10},
      // Agrega más tipos de clientes según corresponda
    ];
  }

  addCustomer(id: string, firstName: string, lastName: string, email: string, phoneNumber: string, customerType: CustomerType) {
    this.newCustomer = { id, firstName, lastName, email, phoneNumber, customerType} as Customer;
    console.log('addCustomer');
    console.log(this.newCustomer);
    alert('aaa');
    console.log(this.customers);
    this.customerService.createCustomer(this.newCustomer).subscribe(() => {
    this.fetchCustomers();
    //this.newCustomer = { firstName: '', lastName: '', email: '', phoneNumber: '', customerType: '' };
    });
  }
}