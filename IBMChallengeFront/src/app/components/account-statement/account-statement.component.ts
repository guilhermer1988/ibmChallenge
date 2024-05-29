import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../models/transaction';
import { AccountService } from '../../services/account.service';
import { Account } from '../../models/account';

@Component({
  selector: 'app-account-statement',
  templateUrl: './account-statement.component.html',
  styleUrls: ['./account-statement.component.scss']
})
export class AccountStatementComponent implements OnInit {
  transactions: Transaction[] = [];
  selectedAccountNumber!: number;
  accounts: Account[] = [];
  accountBalance!: number | null;

  constructor(private transactionService: TransactionService, private accountService: AccountService) { }

  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe(data => this.accounts = data);
  }

  loadTransactions(): void {
    this.transactionService.getTransactionsByAccountNumber(this.selectedAccountNumber).subscribe(data => {
      this.transactions = data;
      this.updateAccountBalance();
    });
  }

  updateAccountBalance(): void {
    const account = this.accounts.find(acc => acc.accountNumber == this.selectedAccountNumber);
    this.accountBalance = account ? account.accountBalance : 0;
  }

  clearTransactions() {
    this.transactions = [];
    this.accountBalance = null;
  }
}
