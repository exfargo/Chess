import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeadComponent } from './head/head.component';
import { FootComponent } from './foot/foot.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ScoreboardComponent } from './scoreboard/scoreboard.component';
import { GameComponent } from './game/game.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HeadComponent,
    FootComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    ScoreboardComponent,
    GameComponent,
    UserComponent,
    RegisterComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
