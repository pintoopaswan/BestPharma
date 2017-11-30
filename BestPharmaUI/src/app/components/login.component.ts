import { Component, OnInit,ViewChild } from '@angular/core';
import {UserService} from '../services/user.service';
import {User} from '../models/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Ng2MessagePopupComponent, Ng2PopupComponent } from 'ng2-popup';



@Component({
  selector: 'app-login',
  templateUrl: '../templates/login.html'
})
export class LoginComponent implements OnInit {
  @ViewChild(Ng2PopupComponent) popup: Ng2PopupComponent;
  user:User;
  loginForm: FormGroup;

  constructor(private fb: FormBuilder,private userservice:UserService, private router: Router){

  }
    ngOnInit() {
      this.userservice.logout();
      this.loginForm = this.fb.group({
        username: ['', [Validators.required, Validators.pattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,3}$")]],
        password: ['', [Validators.required]]
      });
    }

    login(user: User){
      this.userservice.authenticateUserForLogin(user).subscribe(res=>{
        let user=res.data;
        console.log(res.message);
        if (res.status === "SUCCESS") {
          if(user!=null){
    
              localStorage.setItem("isPharmacist", "true");
              localStorage.setItem("roleId", user.roleId);
              localStorage.setItem("role", user.role);
              localStorage.setItem("userId", user.userId);
              localStorage.setItem("username", user.username);
              localStorage.setItem("corporationId", user.corporationId);
              localStorage.setItem("locationId", user.locationId);
              localStorage.setItem("fullName", user.fullName);
              localStorage.setItem("pharmacyName", user.pharmacyName);
              this.router.navigate(['/pharmacistPage']);
         

            // localStorage.setItem("id",user.id);
            // localStorage.setItem("username",user.username);
            // localStorage.setItem("firstname",user.firstName);
            this.router.navigate(['/dashboard']);
          }
      } else {
          this.alert('medium', res);

      }
       
      });

    }

    alert(size, message) {
      this.popup.open(Ng2MessagePopupComponent, {
          classNames: size,
          title: 'Message',
          message: message.message,
          buttons: {
              OK: () => {
                  this.popup.close();
              }
          }
      });
  }
}