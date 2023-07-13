import { User } from "./User"

export interface Vehicle{
    vin: string,
    year: number,
    make: string,
    model: string,
    user: User
}