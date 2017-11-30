import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { UiSwitchModule } from 'ngx-ui-switch';
import { Ng2PopupModule } from 'ng2-popup';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login.component';
import { RegisterComponent } from './components/register.component';
import {NavbarComponent} from './components/navbar.component';
import { UserListComponent } from './components/user-list.components';
import {DashboardComponent} from './components/dashboard.component';
import {BarChartComponent} from './components/bar-chart.component';
import {PieChartComponent} from './components/pie-chart.component';
import {UserService} from './services/user.service';
import { ChartsModule } from 'ng2-charts';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    NavbarComponent,
    RegisterComponent,
    DashboardComponent,
    BarChartComponent,
    PieChartComponent,

 

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    UiSwitchModule,
    Ng2PopupModule,
    ChartsModule

  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
