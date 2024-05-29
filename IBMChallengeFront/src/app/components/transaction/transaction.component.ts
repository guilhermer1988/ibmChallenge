import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';
import { Transaction, TransactionRequest } from '../../models/transaction';
import { AccountService } from '../../services/account.service';
import { Account } from '../../models/account';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {
  transactions: Transaction[] = [];
  accounts: Account[] = [];
  newTransaction: TransactionRequest = new TransactionRequest();

  constructor(private transactionService: TransactionService, private accountService: AccountService) { }

  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe(data => this.accounts = data);
  }

  createTransaction(): void {
    this.transactionService.createTransaction(this.newTransaction).subscribe(() => {
      this.loadTransactions(this.newTransaction.accountNumberOrigin);
      this.newTransaction = new TransactionRequest();
    });
  }

  loadTransactions(accountNumber: number): void {
    this.transactionService.getTransactionsByAccountNumber(accountNumber).subscribe(data => this.transactions = data);
  }

  formatCurrency(value: number): string {
    return value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }
}
