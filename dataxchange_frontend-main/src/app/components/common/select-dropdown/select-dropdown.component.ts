import { Component, EventEmitter, HostListener, Input, OnChanges, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-select-dropdown',
  templateUrl: './select-dropdown.component.html',
  styleUrl: './select-dropdown.component.scss'
})
export class SelectDropdownComponent implements OnChanges {

  @Input() dropdownList: string[] = [];
  @Input() selectedType: string = "";
  @Output() selectedItem = new EventEmitter<any>();

  isOpen = false;

  ngOnChanges(changes: SimpleChanges) {
  }

  toggleDropdown(event: Event) {
    event.stopPropagation();
    this.isOpen = !this.isOpen;
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: Event) {
    this.isOpen = false;
  }

  selectItem(item: string) {
    this.selectedType = item;
    this.isOpen = false;
    this.selectedItem.emit(item);
  }
}
