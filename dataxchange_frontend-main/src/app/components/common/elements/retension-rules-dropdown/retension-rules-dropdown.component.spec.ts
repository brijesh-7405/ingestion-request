import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetensionRulesDropdownComponent } from './retension-rules-dropdown.component';

describe('RetensionRulesDropdownComponent', () => {
  let component: RetensionRulesDropdownComponent;
  let fixture: ComponentFixture<RetensionRulesDropdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RetensionRulesDropdownComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RetensionRulesDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
