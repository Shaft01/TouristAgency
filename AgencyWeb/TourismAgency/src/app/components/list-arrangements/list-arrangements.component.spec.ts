import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListArrangementsComponent } from './list-arrangements.component';

describe('ListArrangementsComponent', () => {
  let component: ListArrangementsComponent;
  let fixture: ComponentFixture<ListArrangementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListArrangementsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListArrangementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
