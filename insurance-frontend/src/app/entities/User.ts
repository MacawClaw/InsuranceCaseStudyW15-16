import { Role } from "./Role"

export interface User {
    username: string,
    password: string,
    enabled: boolean,
    email: string,
    fname: string,
    lname: string,
    phone: string,
    address: string,
    roles: Role[]
}