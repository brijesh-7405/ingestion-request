import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackButtonComponent } from './back-button/back-button.component';
import { MaterialComponentsModule } from './material-components.module';
import { RouterModule } from '@angular/router';
import { RequestConfirmationDialogComponent } from './request-confirmation-dialog/request-confirmation-dialog.component';
import { SelectDropdownComponent } from './select-dropdown/select-dropdown.component';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { FormActionsComponent } from './form-actions/form-actions.component';
import { RequestActionDialogComponent } from './request-action-dialog/request-action-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';



@NgModule({
  declarations: [
    BackButtonComponent,
    SelectDropdownComponent,
    RequestConfirmationDialogComponent,
    FormActionsComponent,
    RequestActionDialogComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatDividerModule,
    MaterialComponentsModule,
    ReactiveFormsModule,
    MatCheckboxModule
  ],
  exports: [
    BackButtonComponent,
    SelectDropdownComponent,
    RequestConfirmationDialogComponent,
    RequestActionDialogComponent,
    FormActionsComponent
  ]
})
export class CommonComponentsModule { }
