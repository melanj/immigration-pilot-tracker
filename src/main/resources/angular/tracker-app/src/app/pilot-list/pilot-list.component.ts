import {Component, OnInit} from '@angular/core';
import {PilotService} from "../pilot.service";
import {Pilot} from "../pilot";

@Component({
  selector: 'app-pilot-list',
  templateUrl: './pilot-list.component.html',
  styleUrls: ['./pilot-list.component.scss']
})
export class PilotListComponent implements OnInit {
  pilots: Pilot[];

  constructor(private pilotService: PilotService) {
  }

  ngOnInit(): void {
    this.pilotService.getPilots()
      .subscribe(pilots => this.pilots = pilots);
  }

}
