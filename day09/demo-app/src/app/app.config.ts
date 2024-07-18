import { APP_INITIALIZER, ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import {
  AuthConfig,
  OAuthService,
  provideOAuthClient,
} from 'angular-oauth2-oidc';

const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:7080/realms/demo-realm',
  tokenEndpoint:
    'http://localhost:7080/realms/demo-realm/protocol/openid-connect/token',
  redirectUri: window.location.origin,
  scope: 'openid profile',
  responseType: 'code',
  clientId: 'demo-client',
};

function initializeOAuth(oAuthService: OAuthService): Promise<void> {
  return new Promise((resolve) => {
    oAuthService.configure(authCodeFlowConfig);
    oAuthService.setupAutomaticSilentRefresh();
    oAuthService.loadDiscoveryDocumentAndLogin().then(() => resolve());
  });
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    provideOAuthClient(),
    {
      provide: APP_INITIALIZER,
      useFactory: (oAuthService: OAuthService) => {
        return () => {
          initializeOAuth(oAuthService);
        };
      },
      multi: true,
      deps: [OAuthService],
    },
  ],
};
