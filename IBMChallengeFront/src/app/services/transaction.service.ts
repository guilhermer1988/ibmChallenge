import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction, TransactionRequest } from '../models/transaction';
import { environment } from '../utils/constants';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private apiUrl = `${environment.IBMChallenge_URL_API}/Transaction`;

  constructor(private http: HttpClient) { }

  getTransactionsByAccountNumber(accountNumber: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.apiUrl}/${accountNumber}`);
  }

  createTransaction(transactionRequest: TransactionRequest): Observable<TransactionRequest> {
    console.log("dasdasdad", transactionRequest)
    return this.http.post<TransactionRequest>(this.apiUrl, transactionRequest);
  }
}
