import { Component, ElementRef } from '@angular/core';
import { FormControl } from '@angular/forms';

let nextId = 0;

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrl: './input.component.scss',
})
export class InputComponent {
  formControl: FormControl<string> = new FormControl();
  label = '';
  isRequired = false;
  placeholder = '';
  tooltip = '';
  id = `input-id-${nextId++}`;

  constructor(public elementRef: ElementRef<HTMLElement>) {}
}
