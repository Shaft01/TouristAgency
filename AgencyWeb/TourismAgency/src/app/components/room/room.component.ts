import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Room } from 'src/app/model/room';
import { RoomService } from 'src/app/service/room.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  @Output() roomChange = new EventEmitter<Room>();
  room: Room=new Room();
  constructor(public activeModal: NgbActiveModal,
    private roomService:RoomService) { }
  
  ngOnInit(): void {
   
  }

  saveRoom(){
    this.roomService.createRoom(this.room).subscribe(response=>{
      this.roomChange.emit(<Room>response);
      this.activeModal.close();
    });

  }
}
