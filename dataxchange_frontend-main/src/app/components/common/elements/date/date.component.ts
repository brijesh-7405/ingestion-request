import { Input, Component, ElementRef, EventEmitter, OnDestroy, forwardRef } from '@angular/core';
import {  ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from '@angular/forms';
import { Subscription } from 'rxjs';

let nextId = 0;

@Component({
  selector: 'app-date',
  templateUrl: './date.component.html',
  styleUrl: './date.component.scss',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DateComponent),
      multi: true,
    }
  ]
})
export class DateComponent implements ControlValueAccessor, OnDestroy {
  formControl: FormControl<Date> = new FormControl();
  label = '';
  isRequired = false;
  @Input() placeholder = 'Date';
  tooltip = '';
  id = `date-id-${nextId++}`

  private onChange: (val: any) => void = () => {};
  private onTouched: () => void = () => {};

  private subscriptions: Subscription[] = [];

  constructor(public elementRef: ElementRef<HTMLElement>) {
    this.subscriptions.push(this.formControl.valueChanges.subscribe((val) => this.onChange(val)))
    this.subscriptions.push(this.formControl.statusChanges.subscribe(() => {
      if (this.formControl.touched) {
        this.onTouched();
      }
    }))
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  writeValue(value: any): void {
    this.formControl.setValue(value);
  }

  registerOnChange(fn: (val: any) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    isDisabled ? this.formControl.disable() : this.formControl.enable();
  }
}
