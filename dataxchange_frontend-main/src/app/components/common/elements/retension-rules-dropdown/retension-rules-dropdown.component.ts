import { Component, ElementRef } from '@angular/core';
import {  FormControl } from '@angular/forms';

interface DropdownOption<T = any> {
  name: string;
  value: T | {
    value: T;
    date: string;
  };
}

@Component({
  selector: 'app-retension-rules-dropdown',
  templateUrl: './retension-rules-dropdown.component.html',
  styleUrl: './retension-rules-dropdown.component.scss'
})
export class RetensionRulesDropdownComponent {
  formControl: FormControl = new FormControl();
  label = '';
  isRequired = false;
  placeholder = '';
  tooltip = '';
  options: DropdownOption[] = [];
  multiple = false;

  constructor(public elementRef: ElementRef<HTMLElement>) {}

  isAsPerContractOption(option: DropdownOption) {
    return typeof option.value === 'object' && option.value.hasOwnProperty('date')
  }
}
