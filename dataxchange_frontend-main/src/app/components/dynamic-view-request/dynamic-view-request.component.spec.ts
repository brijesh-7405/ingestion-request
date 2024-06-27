import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicViewRequestComponent } from './dynamic-view-request.component';

describe('DynamicViewRequestComponent', () => {
  let component: DynamicViewRequestComponent;
  let fixture: ComponentFixture<DynamicViewRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DynamicViewRequestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DynamicViewRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
