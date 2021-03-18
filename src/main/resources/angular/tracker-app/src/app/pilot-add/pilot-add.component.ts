import { Component, OnInit } from '@angular/core';
import {Province} from "../province";
import {Router} from "@angular/router";
import {CommunityService} from "../community.service";
import {ProvinceService} from "../province.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Community} from "../community";
import { PilotService } from '../pilot.service';

@Component({
  selector: 'app-pilot-add',
  templateUrl: './pilot-add.component.html',
  styleUrls: ['./pilot-add.component.scss']
})
export class PilotAddComponent implements OnInit {
  pilot;
  communities: Community[];
  provinces: Province[];

  constructor(private _router: Router,
              private pilotService: PilotService,
              private communityService: CommunityService,
              private provinceService: ProvinceService
  ) {
  }

  ngOnInit(): void {
    this.provinceService.getProvinces()
      .subscribe(provinces => this.provinces = provinces);
    this.communityService.getCommunities()
      .subscribe(community => this.communities = community);
  }

  pilotForm = new FormGroup({
    name: new FormControl('', Validators.required),
    provinces: new FormControl(''),
    communities: new FormControl(''),
  });


  addPilot() {
    this.pilotService.addPilot(this.pilotForm.value)
      .subscribe(pilot => this.pilot = pilot);
    this._router.navigate([''])
  }
}
