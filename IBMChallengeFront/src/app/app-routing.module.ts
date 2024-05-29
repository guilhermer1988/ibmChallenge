import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { ClientComponent } from './components/client/client.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { AccountStatementComponent } from './components/account-statement/account-statement.component';
import { AccountComponent } from './components/account/account.component';

export const DEFAULT_ROUTE = '/home';

const routes: Routes = [
  { path: '', redirectTo: DEFAULT_ROUTE, pathMatch: 'full' },
  { path: 'home', component: MainComponent },
  { path: 'client', component: ClientComponent },
  { path: 'transaction', component: TransactionComponent },
  { path: 'account-statement', component: AccountStatementComponent },
  { path: 'account', component: AccountComponent },
  
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
