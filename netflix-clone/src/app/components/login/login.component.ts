import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import pour ngModel
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  template: `
    <div class="container mt-5">
      <h2>Connexion</h2>
      <form (ngSubmit)="onLogin()">
        <mat-form-field appearance="fill" class="full-width">
          <mat-label>Nom d'utilisateur</mat-label>
          <input matInput [(ngModel)]="username" name="username" required>
        </mat-form-field>

        <mat-form-field appearance="fill" class="full-width">
          <mat-label>Mot de passe</mat-label>
          <input matInput type="password" [(ngModel)]="password" name="password" required>
        </mat-form-field>

        <button mat-raised-button color="primary" type="submit">Se connecter</button>
      </form>
    </div>
  `
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.username, this.password).subscribe(
      (success) => {
        if (success) {
          this.router.navigate(['/']);
        } else {
          alert('Échec de la connexion. Vérifiez vos identifiants.');
        }
      }
    );
  }
}
