import {Component,OnInit} from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
    selector:'navbar',
    templateUrl:'../templates/navbar.html'
})
export class NavbarComponent implements OnInit {
    loginUser: string;
    constructor(private userservice:UserService){}
    ngOnInit(){
        this.getLoginUser();
    }
    getLoginUser() {
        this.loginUser = localStorage.getItem('fullName');
    }

    logout(){
        this.userservice.logout();
    }

}