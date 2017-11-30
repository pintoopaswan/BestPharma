import {Component,OnInit,ViewChild} from '@angular/core';
import {FormBuilder,FormGroup,Validators,FormControl} from '@angular/forms';
import { CustomValidators } from 'ng2-validation';

import {Ng2PopupComponent,Ng2MessagePopupComponent} from 'ng2-popup';
import {Router} from '@angular/router';
import { PasswordValidation } from '../directives/password-validation';

import {UserService} from '../services/user.service';
import {User} from '../models/user'

@Component({
    selector:'register',
    templateUrl:'../templates/register.html'
})
export class RegisterComponent implements OnInit{
    @ViewChild(Ng2PopupComponent) popup:Ng2PopupComponent;
    registerFormGroup:FormGroup;
    form:FormGroup;
    isFormSubmitted:boolean;
    user:User;


    constructor(private fb :FormBuilder,private userservice:UserService,private router:Router){
        this.createForm();

    }
    ngOnInit(){
        this.isFormSubmitted=false;
        console.log('Register Page Called');
        
    }

    createForm() {
        this.registerFormGroup=this.fb.group({
            firstName:['',Validators.required],
            lastName: ['',Validators.required],
            username: ['',Validators.required],
            password: ['',Validators.required],
            confirmPassword: ['',Validators.required],
            phoneNumber: ['',[Validators.required,CustomValidators.number]],
            email: ['',[Validators.required,CustomValidators.email]]
         }, {
            validator: PasswordValidation.MatchPassword // your validation method
          }
        )

    }

    register(value:any,isValid:boolean){
        this.isFormSubmitted=true;
        this.user=value;
        this.user.createdBy='Admin';
        console.log('register component called');
        console.log(isValid);
        if(isValid){
            console.log(this.user);
            this.userservice.register(this.user).subscribe(res=>{
                if(res.status==='SUCCESS'){
                    console.log(res.message);
                    this.alert('medium',res);

                }else{
                    this.isFormSubmitted=true;
                    console.log(res.message);
                    this.alert('medium',res);
                }
            })

        }

    }

    cancelAlert(size, message) {
        this.popup.open(Ng2MessagePopupComponent, {
            classNames: size,
            title: 'Message',
            message: message,
            buttons: {
                OK: () => {
                    this.popup.close();
                    this.router.navigate(['/login'])
                }
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
                    if (message.status === "SUCCESS") {
                        this.router.navigate(['/login']);
                    }
                }
            }
        });
    }

}