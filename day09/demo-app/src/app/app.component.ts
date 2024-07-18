import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  message = '';

  constructor(private oAuthService: OAuthService, private http: HttpClient) {}

  onLogout() {
    this.oAuthService.logOut();
  }

  getHelloMsg() {
    this.http
      .get<{ message: string }>('http://localhost:8001/hello', {
        headers: {
          Authorization: `Bearer ${this.oAuthService.getAccessToken()}`,
        },
      })
      .subscribe(
        (result) => {
          alert(this.message);
          this.message = result.message;
        },
        (err) => console.log(err)
      );
  }
}
