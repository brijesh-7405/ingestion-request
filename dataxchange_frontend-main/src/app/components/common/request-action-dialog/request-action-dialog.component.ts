import { Component, Inject, Input } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AppService } from '../../../app.service';
import { DecisionRequestDTO, IngestionRequest } from '../../../models/models';

@Component({
  selector: 'app-request-action-dialog',
  templateUrl: './request-action-dialog.component.html',
  styleUrl: './request-action-dialog.component.scss'
})
export class RequestActionDialogComponent {

  submitted = false;
  dialogInfo = { title: "", type: "submit" }
  options: any = [];
  ingestionRequestId: number = 0;

  form: FormGroup = this.formBuilder.group({});

  constructor(
    public dialogRef: MatDialogRef<RequestActionDialogComponent>,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private appService: AppService,
  ) {
    this.options = data.options;
    this.form = data.form;
    this.dialogInfo = data.dialogInfo;
    this.ingestionRequestId = data.ingestionRequestId;
  }

  isRequired(controlName: string): boolean {
    const control = this.form.get(controlName);
    return control && control.validator && control.validator({} as any)?.['required'];
  }

  get f() { return this.form.controls; }

  hasControl(controlName: string): boolean {
    return !!this.form.get(controlName);
  }

  onActionSubmit(): void {
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }

    const payload: DecisionRequestDTO = {
      decisionComments: this.form.value["comment"],
      notifyThroughEmail: this.form.value["notify"],
      rejectionReason: this.form.value["reasonForRejection"],
      existingDataLocationIdentified: this.form.value["url"]
    };

    const requestObservable = this.hasControl('reasonForRejection')
      ? this.appService.rejectRequest(this.ingestionRequestId, payload)
      : this.appService.approveRequest(this.ingestionRequestId, payload);

    requestObservable.subscribe(res => this.dialogRef.close(res));
  }
  onCloseDialog(): void {
    this.dialogRef.close();
  }
}
