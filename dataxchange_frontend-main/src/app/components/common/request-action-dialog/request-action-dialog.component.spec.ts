import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestActionDialogComponent } from './request-action-dialog.component';

describe('RequestActionDialogComponent', () => {
  let component: RequestActionDialogComponent;
  let fixture: ComponentFixture<RequestActionDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RequestActionDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RequestActionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
