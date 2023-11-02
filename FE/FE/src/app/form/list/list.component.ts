import { Component, OnInit } from '@angular/core';
import {FormService} from "../../service/form.service";
import {FormDto} from "../../dto/formDto";
import {PageForm} from "../../model/pageForm";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  // @ts-ignore
  formList: PageForm;

  constructor(private formService: FormService) { }

  ngOnInit(): void {
    this.findAllForm(0);
  }

  // tslint:disable-next-line:typedef
  findAllForm(pageNumber: number) {
    this.formService.formList(pageNumber).subscribe(
      data => {
        this.formList = data;
      },
    );
  }

  // tslint:disable-next-line:typedef
  gotoPage(pageNumber: number) {
    this.findAllForm(pageNumber);
  }

}
