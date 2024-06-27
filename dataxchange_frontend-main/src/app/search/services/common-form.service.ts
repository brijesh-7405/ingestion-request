import { Injectable } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import moment from 'moment';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonFormService {
  public AdGroups:any = [];
  public environment:any = [];
  status = new BehaviorSubject<string>('');
  statusValue = this.status.asObservable();
  constructor() { }
  markGroupDirty(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach((key:string) => {
      if (formGroup.get(key) instanceof FormControl) {
        this.markControlDirty(formGroup.get(key) as FormControl);
      } else if (formGroup.get(key) instanceof FormGroup) {
        this.markGroupDirty(formGroup.get(key) as FormGroup);
      } else if (formGroup.get(key) instanceof FormArray) {
        this.markArrayDirty(formGroup.get(key) as FormArray);
      }
    });
  }
  markArrayDirty(formArray: FormArray) {
    formArray.controls.forEach(control => {
      if (control instanceof FormControl) {
        this.markControlDirty(control as FormControl);
      } else if (control instanceof FormGroup) {
        this.markGroupDirty(control as FormGroup);
      } else if (control instanceof FormArray) {
        this.markArrayDirty(control as FormArray);
      }
    });
  }
  markControlDirty(formControl: FormControl) {
    formControl.markAsDirty();
  }

  //arragen controls method
  arragenFormControls(controlsArr: any[], key: any, toIndex: number) {
    let fromIndex = controlsArr.findIndex(value => value.key == key);
    if (fromIndex < 0 || fromIndex >= controlsArr.length || toIndex < 0 || toIndex >= controlsArr.length) {
      return;
    }
    const extractedObject = controlsArr.splice(fromIndex, 1)[0];
    controlsArr.splice(toIndex, 0, extractedObject);
    return controlsArr;
  }

  //method to get date object
  getDateObject(dateString: string) {
    if (dateString) {
      let parsedDate = moment(dateString, "DD-MM-YYYY");
      let formattedDate = parsedDate.format("DD MMM YYYY");
      return new Date(formattedDate);
    }
    return '';
  }

  //method to get fomatted date in string
  getFormattedDateString(dateString: string) {
    if (dateString) {
      let parsedDate = moment(dateString, "DD-MM-YYYY");
      return parsedDate.format("DD MMM YYYY");
    }
    return '';
  }

  //replace space with "-"
  replaceSpaceWithHyphen(str: string) {
    return str ? str.replace(/\s+/g, '-') : '';
  }


  //Method to save form during file upload in domain reistration
  getSaveValues(data: string){
    this.status.next(data)
  }
}
