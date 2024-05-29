import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/clients';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';


@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent implements OnInit {
  clients: Client[] = [];
  newClient: Client = new Client();
  editingClient: Client | null = null;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getClients().subscribe(data => this.clients = data);
  }

  saveClient(): void {
    if (this.editingClient) {
      this.clientService.updateClient(this.editingClient.id, this.newClient).subscribe(() => {
        this.loadClients();
        this.editingClient = null;
        this.newClient = new Client();
      });
    } else {
      this.clientService.createClient(this.newClient).subscribe(() => {
        this.loadClients();
        this.newClient = new Client();
      });
    }
  }

  editClient(client: Client): void {
    this.newClient = { ...client };
    this.editingClient = client;
  }

  deleteClient(id: number): void {
    this.clientService.deleteClient(id).subscribe(() => this.loadClients());
  }
}
