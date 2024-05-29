import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account.service';
import { Account } from '../../models/account';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/clients';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  accounts: Account[] = [];
  clients: Client[] = [];
  newAccount: Account = {
    id: 0,
    accountNumber: null,
    accountBalance: 0,
    client: {
      id: null,
      name: '',
      email: '',
      address: ''
    }
  };
  editingAccount: Account | null = null;

  constructor(private accountService: AccountService, 
    private clientService: ClientService) { }

  ngOnInit(): void {
    this.getAccounts();
    this.loadClients();
  }

  getAccounts(): void {
    this.accountService.getAccounts().subscribe(accounts => {
      this.accounts = accounts;
    });
  }

  createAccount(): void {
    this.accountService.createAccount(this.newAccount).subscribe(account => {
      this.accounts.push(account);
      this.resetNewAccountForm();
    });
  }

  editAccount(account: Account): void {
    this.newAccount = { ...account};
    this.editingAccount = account
  }

  deleteAccount(id: number): void {
    this.accountService.deleteAccount(id).subscribe(() => {
      this.accounts = this.accounts.filter(account => account.id !== id);
    });
  }

  formatCurrency(value: number): string {
    return value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  loadClients(): void {
    this.clientService.getClients().subscribe(data => this.clients = data);
  }

  resetNewAccountForm(): void {
    this.newAccount = {
      id: 0,
      accountNumber: null,
      accountBalance: 0,
      client: {
        id: null,
        name: '',
        email: '',
        address: ''
      }
    };
  }
}
