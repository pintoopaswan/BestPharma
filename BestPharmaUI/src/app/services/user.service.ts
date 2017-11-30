import { Injectable } from "@angular/core";
import { Http, Headers, Response, RequestOptions, RequestMethod, URLSearchParams } from "@angular/http";
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { User } from '../models/user';

 

@Injectable()
export class UserService {
    private apiServiceBaseUri:string='http://localhost:8080/' ;
    private authenticateUser = 'login/authenticate';
    private getUserList='user/list';
    private registerUser= 'user/register';



    constructor(private _http: Http, private router: Router) {
    }

    authenticateUserForLogin(user: User): Observable<any> {
        // let bodyString: string = JSON.stringify(user);
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ method: RequestMethod.Post, headers: headers });
        // return this._http.post(this.apiServiceBaseUri+this.authenticateUser, bodyString, options)
        //     .map((response: Response) => {
        //         //localStorage.setItem("token", response.headers.get('Authorization'));
        //         return response.json();
        //     })
        //     .catch((error: any) => Observable.throw(error || 'Server error'));
        let bodyString: string = JSON.stringify(user);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ method: RequestMethod.Post, headers: headers });
        return this._http.post("http://fastrx.southcentralus.cloudapp.azure.com:8080/fastrx/login/adminAuthenticate", bodyString, options)
            .map((response: Response) => {
                localStorage.setItem("token", response.headers.get('Authorization'));
                return response.json();
            })
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    register(user:User){
        console.log('register service called');
        let bodyString: string =JSON.stringify(user);
        let headers= new Headers({'Content-Type':'application/json'});
        let options= new RequestOptions({method:RequestMethod.Post,headers:headers});
        return this._http.post(this.apiServiceBaseUri+this.registerUser,bodyString,options).map((response:Response)=>{
            return response.json();
        }).catch((error:any)=>Observable.throw(error || 'Server Error'))
    }

    logout(){
        localStorage.clear();
        this.router.navigate(['/login']);
    }

    userList(){
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ method: RequestMethod.Get, headers: headers });
        return this._http.get(this.apiServiceBaseUri+this.getUserList, options)
            .map((response: Response) => {
                return response.json();
            })
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    UserCount(){
        let headers = new Headers({ 'Content-Type': 'application/json' });
        headers.append('Authorization', localStorage.getItem('token'));
        console.log(localStorage.getItem('token'));
        let options = new RequestOptions({ method: RequestMethod.Get, headers: headers });
        return this._http.get("http://fastrx.southcentralus.cloudapp.azure.com:8080/fastrx/user/location", options)
            .map((response: Response) => {
                return response.json();
            })
            .catch((error: any) => Observable.throw(error || 'Server error'));

    }

    corporationCount(){
        let headers = new Headers({ 'Content-Type': 'application/json' });
        headers.append('Authorization', localStorage.getItem('token'));
        console.log(localStorage.getItem('token'));
        let options = new RequestOptions({ method: RequestMethod.Get, headers: headers });
        return this._http.get("http://fastrx.southcentralus.cloudapp.azure.com:8080/fastrx/pharmacy/corporation/list", options)
            .map((response: Response) => {
                return response.json();
            })
            .catch((error: any) => Observable.throw(error || 'Server error'));

    }

    locationCount(){
        let headers = new Headers({ 'Content-Type': 'application/json' });
        headers.append('Authorization', localStorage.getItem('token'));
        console.log(localStorage.getItem('token'));
        let options = new RequestOptions({ method: RequestMethod.Get, headers: headers });
        return this._http.get("http://fastrx.southcentralus.cloudapp.azure.com:8080/fastrx/pharmacyLocation/getAllLocations", options)
            .map((response: Response) => {
                return response.json();
            })
            .catch((error: any) => Observable.throw(error || 'Server error'));

    }
}