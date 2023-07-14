import { Role } from "./Role"

export interface User {
    username: string,
    password: string,
    enabled: boolean,
    email: string,
    firstName: string,
    lastName: string,
    phone: string,
    address: string,
    roles: Role[]
}