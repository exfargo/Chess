import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GameComponent} from "./game/game.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {UserComponent} from "./user/user.component";
import {LeaderboardComponent} from "./leaderboard/leaderboard.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path: 'home', component:HomeComponent},
  {path: 'game', component: GameComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user', component: UserComponent},
  {path: 'leaderboard', component: LeaderboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
