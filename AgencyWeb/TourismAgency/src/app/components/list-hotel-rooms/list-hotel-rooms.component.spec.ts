import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHotelRoomsComponent } from './list-hotel-rooms.component';

describe('ListHotelRoomsComponent', () => {
  let component: ListHotelRoomsComponent;
  let fixture: ComponentFixture<ListHotelRoomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListHotelRoomsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListHotelRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
