import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {finalize} from 'rxjs/operators';
import {FormCreateDto} from "../../dto/formCreateDto";
import {AngularFireStorage} from "@angular/fire/compat/storage";
import {FormService} from "../../service/form.service";
import {Router} from "@angular/router";
import {ImgDto} from "../../dto/ImgDto";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  // @ts-ignore
  rfCreateForm: FormGroup;
  src: string | undefined;
  // @ts-ignore
  form: FormCreateDto;
  // @ts-ignore
  readFile: any[] | [];
  imgs: any[] = [];
  selectedFile: any[] = [];
  isSubmitting = false;
  constructor(private storage: AngularFireStorage,
              private formService: FormService,
              private build: FormBuilder,
              private toast: ToastrService,
              private route: Router) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.rfCreateForm = this.build.group({
      name: ['', [Validators.required,
        Validators.maxLength(256)]],
      description: ['', [Validators.required,
        Validators.maxLength(3000)]],
      images: []
    });
  }

  createNewForm(): void {
    if (this.rfCreateForm.invalid) {
      this.toast.error('Create New Product Failed');
      return;
    }
    // @ts-ignore
    if (!this.rfCreateForm.get('images').value || this.rfCreateForm.get('images').value.length === 0) {
      this.toast.error('You Must Select An Image');
      return;
    }
    if (this.rfCreateForm.valid) {
      this.isSubmitting = true;
      this.form = this.rfCreateForm.value;
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.readFile.length; i++) {
        const selectedImage = this.readFile[i];
        const n = Date.now();
        const filePath = `RoomsImages/${n}`;
        const fileRef = this.storage.ref(filePath);
        this.storage.upload(filePath, selectedImage).snapshotChanges().pipe(
          // @ts-ignore
          finalize(() => {
            fileRef.getDownloadURL().subscribe(ulr => {
              this.imgs.push(ulr);
            });
          })
        ).subscribe(() => {
        });
      }
      setTimeout(() => {
        this.formService.createForm(this.form).subscribe(data => {
          if (this.imgs.length !== 0) {
            // tslint:disable-next-line:prefer-for-of no-shadowed-variable
            for (let i = 0; i < this.imgs.length; i++) {
              const image: ImgDto = {
                url: this.imgs[i],
                formId: data.id
              };
              this.formService.createImg(image).subscribe(() => {
              });
            }
          }
          this.isSubmitting = false;
          this.toast.success('Add New Success');
          this.route.navigateByUrl('');
        });
      }, 10000);
    } else {
      this.toast.error('Create New Product Failed');
    }
  }

  // tslint:disable-next-line:typedef
  showPreview(event: any) {
    const files = event.target.files;
    this.readFile = event.target.files;
    if ((files.length + this.selectedFile.length) < 2) {
      for (const file of files) {
        if (file.size > 1048576) {
          this.toast.error('Image Size Exceeds 1MB');
          this.rfCreateForm.patchValue({images: []});
          // @ts-ignore
          this.rfCreateForm.controls.images.setValue([]);
          break;
        }
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.selectedFile.push(e.target.result);
        };
        reader.readAsDataURL(file);
      }
    } else {
      this.toast.error('Please Do Not Select More Than 1 Image');
      this.rfCreateForm.patchValue({images: []});
      // @ts-ignore
      this.rfCreateForm.controls.images.setValue([]);
    }
  }

  // tslint:disable-next-line:typedef
  deleteImageNew(index: number) {
    // tslint:disable-next-line:triple-equals
    if (this.selectedFile.length == 1) {
      this.selectedFile.splice(index, 1);
      this.toast.error('You Have Deleted 1 Image');
      // @ts-ignore
      this.rfCreateForm.controls.images.setValue([]);
    } else {
      this.selectedFile.splice(index, 1);
      this.toast.error('You Have Deleted 1 Image');
    }
  }

}
