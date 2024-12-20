import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.username, this.password).subscribe(
      (success) => {
        if (success) {
          this.router.navigate(['/']); // Redirige vers la page d'accueil après connexion
        } else {
          alert('Échec de la connexion. Vérifiez vos identifiants.');
        }
      },
      (error) => {
        console.error('Erreur lors de la connexion:', error);
        alert('Une erreur est survenue. Réessayez plus tard.');
      }
    );
  }
}
