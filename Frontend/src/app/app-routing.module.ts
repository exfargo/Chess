import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GameComponent} from './game/game.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {UserComponent} from './user/user.component';
import {LeaderboardComponent} from './lidlboard/leaderboard.component';
import {HomeComponent} from './home/home.component';
import {UsersComponent} from './users/users.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {BlameComponent} from './blame/blame.component';

const routes: Routes = [

  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path: 'user/:id', component: UserComponent},
  {path: 'game/:id', component: GameComponent},

  {path: 'lidlboard', component: LeaderboardComponent},
  {path: 'users', component: UsersComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'blame', component: BlameComponent},
  {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
