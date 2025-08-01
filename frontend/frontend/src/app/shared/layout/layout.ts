import { Component } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NgFor, NgIf } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.html',
  styleUrls: ['./layout.css'],
  standalone: true,
  
  imports: [
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    RouterModule,
    MatButtonModule,
    MatIconModule,
    MatSlideToggleModule,
    NgIf,
    NgFor
  ],

})
export class LayoutComponent {

  username = '';
  isDarkMode = false;


  constructor(public authService: AuthService, private router: Router) {
    const decoded = this.authService.getDecodedToken();
    this.username = decoded ? decoded.sub : '';
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
    window.location.reload(); 
  }
  toggleDarkMode() {
    this.isDarkMode = !this.isDarkMode;
    this.setDarkMode(this.isDarkMode);
    localStorage.setItem('darkMode', this.isDarkMode.toString());
  }
  
  setDarkMode(enable: boolean) {
    document.body.classList.toggle('dark-mode', enable);
    document.documentElement.classList.toggle('dark-mode', enable);
  }
  
  ngOnInit() {
    // Al iniciar, lee la preferencia guardada en localStorage
    this.isDarkMode = localStorage.getItem('darkMode') === 'true';
    this.setDarkMode(this.isDarkMode);
  }
}
