import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicDomainFormComponent } from './dynamic-domain-form.component';

describe('DynamicDomainFormComponent', () => {
  let component: DynamicDomainFormComponent;
  let fixture: ComponentFixture<DynamicDomainFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DynamicDomainFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DynamicDomainFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
