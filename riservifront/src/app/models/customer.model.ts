import { CustomerType } from "./customertype.model";

export interface Customer {
    id?: string;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber?: string;
    customerType: CustomerType;
}