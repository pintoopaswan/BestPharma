import {Component,OnInit,ViewChild} from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
    selector:'dashboard',
    templateUrl:'../templates/dashboard.html'
})
export class DashboardComponent implements OnInit{
    userCount:number;
    locationCount:number;
    pharmacyCount:number;
    constructor(private userService:UserService){

    }

    ngOnInit(){
        console.log('Dashboard called');
        this.userService.UserCount().subscribe(res=>{
            let userList=res.data;
            this.userCount=userList.length;
            console.log(userList.length);
        })
        this.userService.corporationCount().subscribe(res=>{
            let corpList=res.data;
            this.pharmacyCount=corpList.length;
            console.log(corpList.length);
        })
        this.userService.locationCount().subscribe(res=>{
            let locList=res.data;
            this.locationCount=locList.length;
            console.log(locList.length);
        })

        
    }

}