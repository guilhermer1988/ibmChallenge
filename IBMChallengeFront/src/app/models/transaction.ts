import { Account } from "./account";

export class Transaction {
    id!: number;
    accountOrigin!: Account;
    accountDestiny!: Account;
    value!: number;
    createAt!: string; // ISO date string
  }
  
  export class TransactionRequest {
    accountNumberOrigin!: number;
    accountNumberDestination!: number;
    value!: number;
  }
  