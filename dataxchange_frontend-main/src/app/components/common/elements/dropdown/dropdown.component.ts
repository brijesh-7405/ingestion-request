import { Component, ElementRef } from '@angular/core';
import {  FormControl } from '@angular/forms';

interface DropdownOption {
  name: string;
  value: any;
}

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.scss'
})
export class DropdownComponent {
  formControl: FormControl = new FormControl();
  label = '';
  isRequired = false;
  placeholder = '';
  tooltip = '';
  options: DropdownOption[] = [];
  multiple = false;

  constructor(public elementRef: ElementRef<HTMLElement>) {}
}
