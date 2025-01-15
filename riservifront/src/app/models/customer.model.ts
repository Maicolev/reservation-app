import { CustomerType } from "./customertype.model";

export interface Customer {
    id?: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber?: string;
    customerType: CustomerType;
}