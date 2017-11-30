import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {User} from '../models/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';



@Component({
  selector: 'user-list',
  templateUrl: '../templates/user-list.html'
})
export class UserListComponent implements OnInit {
  user_list:User[];
  searchUserForm: FormGroup;

  constructor(private fb: FormBuilder,private userservice:UserService){

  }
    ngOnInit() {
        console.log('user-list called');
        this.userList();
        this.searchUserForm = this.fb.group({
            username: ['', [Validators.required]],
            pharmacyPhone: ['', [Validators.required]],
        });
    }

    userList(){
      this.userservice.userList().subscribe(res=>{
        this.user_list=res.data;
        console.log(res.message);
      });

    }
}