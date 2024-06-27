import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DdfRequestComponent } from './ddf-request.component';

describe('DdfRequestComponent', () => {
  let component: DdfRequestComponent;
  let fixture: ComponentFixture<DdfRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DdfRequestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DdfRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
