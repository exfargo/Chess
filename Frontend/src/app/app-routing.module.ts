import {EventEmitter, Input, NgModule, Output} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GameComponent} from './game/game.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {UserComponent} from './user/user.component';
import {LeaderboardComponent} from './lidlboard/leaderboard.component';
import {HomeComponent} from './home/home.component';
import {UsersComponent} from './users/users.component';
import {DashboardComponent} from './dashboard/dashboard.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'game', component: GameComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'lidlboard', component: LeaderboardComponent},
  {path: 'users', component: UsersComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
