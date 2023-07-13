import { Status } from "./Status"
import { User } from "./User"
import { Vehicle } from "./Vehicle"

export interface Claim{
    claimId: number,
    claimDescription: string,
    claimCost: number,
    adjusterNotes: string,
    vehicle: Vehicle,
    claimStatus: Status,
    user: User
}