import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, MatToolbarModule],
  template: `
    <mat-toolbar color="primary">
      <span>Netflix</span>
      <span class="spacer"></span>
      <a mat-button routerLink="/">Accueil</a>
      <a mat-button routerLink="/login">Connexion</a>
    </mat-toolbar>
    <router-outlet></router-outlet>
  `,
  styles: [`
    .spacer { flex: 1 1 auto; }
    mat-toolbar { margin-bottom: 20px; }
  `]
})
export class AppComponent {}
