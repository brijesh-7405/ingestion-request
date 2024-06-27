import { Component, ElementRef } from '@angular/core';
import {  FormControl } from '@angular/forms';


let nextId = 0

@Component({
  selector: 'app-textarea',
  templateUrl: './textarea.component.html',
  styleUrl: './textarea.component.scss'
})
export class TextareaComponent {
  formControl: FormControl<string> = new FormControl();
  label = '';
  isRequired = false;
  placeholder = '';
  tooltip = '';
  id = `textarea-id-${nextId++}`

  constructor(public elementRef: ElementRef<HTMLElement>) {}
}
