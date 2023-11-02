import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Form} from "../model/form";
import {ImgDto} from "../dto/ImgDto";

@Injectable({
  providedIn: 'root'
})
export class FormService {

  api_url = 'http://localhost:8080/form'

  constructor(private httpClient: HttpClient) { }

  formList(pageNumber: number): Observable<any> {
    return this.httpClient.get<any>(this.api_url + '/list'+ '?page=' + pageNumber);
  }

  createForm(form: any): Observable<Form> {
    return this.httpClient.post<Form>(this.api_url + '/create', form);
  }

  createImg(image: any): Observable<ImgDto> {
    return this.httpClient.post<ImgDto>(this.api_url + '/img/create', image);
  }
}
