import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputComponent } from '../common/elements/input/input.component';
import { DropdownComponent } from '../common/elements/dropdown/dropdown.component';
import { DateComponent } from '../common/elements/date/date.component';
import { MaterialComponentsModule } from '../common/material-components.module';
import { TextareaComponent } from '../common/elements/textarea/textarea.component';
import { SearchableDropdownComponent } from '../common/elements/searchable-dropdown/searchable-dropdown.component';
import { RadiosComponent } from '../common/elements/radios/radios.component';
import { DynamicFormComponent, DynamicFormContentDirective } from './dynamic-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RetensionRulesDropdownComponent } from '../common/elements/retension-rules-dropdown/retension-rules-dropdown.component';


@NgModule({
  declarations: [
    InputComponent,
    DropdownComponent,
    DateComponent,
    TextareaComponent,
    SearchableDropdownComponent,
    RadiosComponent,
    DynamicFormComponent,
    RetensionRulesDropdownComponent,
    DynamicFormContentDirective,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialComponentsModule,
  ],
  exports: [
    InputComponent,
    DropdownComponent,
    DateComponent,
    TextareaComponent,
    SearchableDropdownComponent,
    RadiosComponent,
    DynamicFormComponent,
  ]
})
export class DynamicFormModule { }
