import { Component, ElementRef } from '@angular/core';
import {  FormControl } from '@angular/forms';

interface RadioOption {
  name: string;
  value: any;
  tooltip?: string;
}

@Component({
  selector: 'app-radios',
  templateUrl: './radios.component.html',
  styleUrl: './radios.component.scss'
})
export class RadiosComponent {
  formControl: FormControl = new FormControl();
  label = '';
  isRequired = false;
  placeholder = '';
  tooltip = '';
  options: RadioOption[] = [];

  constructor(public elementRef: ElementRef<HTMLElement>) {}
}
