import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Room } from 'src/app/model/room';
import { RoomService } from 'src/app/service/room.service';
import { RoomComponent } from '../room/room.component';

@Component({
  selector: 'app-list-rooms',
  templateUrl: './list-rooms.component.html',
  styleUrls: ['./list-rooms.component.css']
})
export class ListRoomsComponent implements OnInit {
  rooms:Room[]=[];
  constructor(private roomService:RoomService,private modalService: NgbModal) { }

  ngOnInit(): void {

    this.loadRooms();
  }

  createRoom(){
    const modalRef =this.modalService.open(RoomComponent);
    modalRef.componentInstance.roomChange.subscribe(response=>{
      this.loadRooms();
    });
  }
  loadRooms(){
    this.roomService.getAllRooms().subscribe(response=>{
      this.rooms = response;
    });
  }
}
