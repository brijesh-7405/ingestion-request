import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ComponentRef,
  Directive,
  EventEmitter,
  HostBinding,
  Input,
  NgZone,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild,
  ViewContainerRef,
} from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { InputComponent } from '../common/elements/input/input.component';
import { TextareaComponent } from '../common/elements/textarea/textarea.component';
import { DropdownComponent } from '../common/elements/dropdown/dropdown.component';
import { SearchableDropdownComponent } from '../common/elements/searchable-dropdown/searchable-dropdown.component';
import { DateComponent } from '../common/elements/date/date.component';
import { RadiosComponent } from '../common/elements/radios/radios.component';
import { RetensionRulesDropdownComponent } from '../common/elements/retension-rules-dropdown/retension-rules-dropdown.component';

export type ControlMetadata = {
  name: string;
  type: string;
  isReq: boolean;
  value: any;
  key: string;
  valmsg: string;
  tooltip: boolean;
  placeHolder: string;
  isDynamic?: boolean;
  multi?: boolean;
  data?: any[];
};

@Directive({
  selector: '[appDynamicFormContent]'
})
export class DynamicFormContentDirective {
  constructor(
    public viewContainerRef: ViewContainerRef
  ) {}
}

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrl: './dynamic-form.component.scss',
})
export class DynamicFormComponent implements AfterViewInit {
  @ViewChild(DynamicFormContentDirective) dynamicFormContent!: DynamicFormContentDirective;

  @Input() controlsData: ControlMetadata[] = [];
  @Input() form: FormGroup = this.formBuilder.group({});
  @HostBinding('class') @Input() class = '';

  @Output() formChange = new EventEmitter<FormGroup>();


  formControlComponents = new Map<string, ComponentRef<any>>();

  constructor(
    private formBuilder: FormBuilder,
    private cdr: ChangeDetectorRef
  ) {}

  ngAfterViewInit(): void {
    this.generateUI();
  }

  private generateUI(): void {
    Object.keys(this.form.controls).forEach((name: string) => {
      this.form.removeControl(name);
    });

    this.eraseControls();
    this.controlsData.forEach((data: ControlMetadata) => {
      this.insertControl(data);
    });

    this.formChange.emit(this.form);
    this.cdr.detectChanges();
  }

  insertControl(data: ControlMetadata): void {
    switch (data.type) {
      case 'input': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<InputComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(InputComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);

        break;
      }

      case 'textarea': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<TextareaComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(TextareaComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);
        
        break;
      }

      case 'dropdown': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<DropdownComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(DropdownComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.options = data.data || [];
        compRef.instance.multiple = !!data.multi;
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);

        break;
      }

      case 'searchable-dropdown': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<SearchableDropdownComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(SearchableDropdownComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.options = data.data || [];
        compRef.instance.multiple = !!data.multi;
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);

        break;
      }

      case 'retension-rules-dropdown': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<RetensionRulesDropdownComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(RetensionRulesDropdownComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.options = data.data || [];
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);

        break;
      }

      case 'radio': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<RadiosComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(RadiosComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.options = data.data || [];
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);

        break;
      }

      case 'date': {
        const formControl: FormControl = this.formBuilder.control(data.value);
        this.form.addControl(data.key, formControl);

        const compRef: ComponentRef<DateComponent> =
          this.dynamicFormContent.viewContainerRef.createComponent(DateComponent);
        compRef.instance.formControl = formControl;
        compRef.instance.label = data.name;
        compRef.instance.isRequired = data.isReq;
        compRef.instance.tooltip = data.tooltip ? data.valmsg : '';
        compRef.instance.placeholder = data.placeHolder;
        compRef.instance.elementRef.nativeElement.classList.add(data.key);

        this.formControlComponents.set(data.key, compRef);

        break;
      }
    }
  }

  eraseControls(): void {
    this.dynamicFormContent.viewContainerRef.clear();
    this.formControlComponents.clear();
  }

  showControl(key: string): void {
    const compRef = this.formControlComponents.get(key)!;

    this.form.addControl(key, compRef.instance.formControl);
    compRef.instance.elementRef.nativeElement.style.display = '';
  }

  hideControl(key: string): void {
    const compRef = this.formControlComponents.get(key)!;

    this.form.removeControl(key);
    compRef.instance.elementRef.nativeElement.style.display = 'none';
  }
}
