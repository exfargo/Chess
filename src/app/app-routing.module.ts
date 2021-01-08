import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import {ScoreboardComponent} from './scoreboard/scoreboard.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {GameComponent} from './game/game.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'user', component: UserComponent},
  {path: 'game', component: GameComponent},
  {path: 'scoreboard', component: ScoreboardComponent},
  {path: 'user/login', component: LoginComponent},
  {path: 'user/register', component: RegisterComponent},
  {path: '**', component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
