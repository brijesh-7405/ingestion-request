import { NgModule } from '@angular/core';
import {MatTooltipModule} from '@angular/material/tooltip';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import { MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter } from '@angular/material-moment-adapter';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { MatChipsModule} from '@angular/material/chips';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import {MatSelectModule} from '@angular/material/select';
import { MatFormFieldModule } from "@angular/material/form-field";
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import {MatRadioModule} from '@angular/material/radio';
import { PortalModule } from '@angular/cdk/portal';
import { MatDividerModule } from '@angular/material/divider';
import {MatToolbarModule} from '@angular/material/toolbar';

export const MY_FORMATS = {
  parse: {
      dateInput: 'DD MMM YYYY',
  },
  display: {
      dateInput: 'DD MMM YYYY',
      monthYearLabel: 'DD MMM YYYY',
      dateA11yLabel: 'LL',
      monthYearA11yLabel: 'DD MMM YYYY'
  },
};

const MATERIAL_MODULES = [
  MatTooltipModule,
  MatDatepickerModule,
  MatStepperModule,
  MatButtonModule,
  MatChipsModule,
  CdkTableModule,
  CdkTreeModule,
  DragDropModule,
  MatAutocompleteModule,
  ScrollingModule,
  MatSelectModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatMenuModule,
  MatRadioModule,
  PortalModule,
  MatDividerModule,
  MatToolbarModule,
]

@NgModule({
  imports: [
    MATERIAL_MODULES
  ],
  exports: [
    MATERIAL_MODULES
  ],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
    provideNativeDateAdapter()
  ],
})
export class MaterialComponentsModule { }
