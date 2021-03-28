import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlameComponent } from './blame.component';

describe('BlameComponent', () => {
  let component: BlameComponent;
  let fixture: ComponentFixture<BlameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BlameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
