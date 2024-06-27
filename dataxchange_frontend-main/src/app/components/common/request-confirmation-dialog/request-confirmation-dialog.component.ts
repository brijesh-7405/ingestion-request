import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-request-confirmation-dialog',
  templateUrl: './request-confirmation-dialog.component.html',
  styleUrl: './request-confirmation-dialog.component.scss'
})
export class RequestConfirmationDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<RequestConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
   }

  viewRequest(): void {
    this.dialogRef.close('view');
  }
}
