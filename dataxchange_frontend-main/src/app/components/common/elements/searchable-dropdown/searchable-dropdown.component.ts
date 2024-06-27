import { Component, ElementRef } from '@angular/core';
import { FormControl } from '@angular/forms';

interface SearchableDropdownOption {
  name: string;
  value: any;
}

@Component({
  selector: 'app-searchable-dropdown',
  templateUrl: './searchable-dropdown.component.html',
  styleUrl: './searchable-dropdown.component.scss',
})
export class SearchableDropdownComponent {
  formControl: FormControl = new FormControl();
  label = '';
  isRequired = false;
  placeholder = '';
  tooltip = '';
  options: SearchableDropdownOption[] = [];
  multiple = false;

  get filteredOptions(): SearchableDropdownOption[] {
    if (this.searchText) {
      const insert = this.options.filter((option) =>
        option.name
          .toLocaleLowerCase()
          .includes(this.searchText.toLocaleLowerCase())
      );
      this._filteredOptions.length = 0;
      this._filteredOptions.push(...insert);
      return this._filteredOptions;
    }
    return this.options;
  }
  private _filteredOptions: SearchableDropdownOption[] = [];

  searchText = '';

  constructor(public elementRef: ElementRef<HTMLElement>) {}

  clearSearchText() {
    this.searchText = ''
  }

  util_stopSelectionOnFiltering = stopSelectionOnFiltering;
}

function stopSelectionOnFiltering($event: KeyboardEvent) {
  $event.stopPropagation();
}
