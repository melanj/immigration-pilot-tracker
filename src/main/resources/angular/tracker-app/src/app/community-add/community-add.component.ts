import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CommunityService} from "../community.service";
import {ProvinceService} from "../province.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Province} from "../province";

@Component({
  selector: 'app-community-add',
  templateUrl: './community-add.component.html',
  styleUrls: ['./community-add.component.scss']
})
export class CommunityAddComponent implements OnInit {
  community;
  provinces: Province[];

  constructor(private _router: Router,
              private communityService: CommunityService,
              private provinceService: ProvinceService
  ) {
  }

  ngOnInit(): void {
    this.provinceService.getProvinces()
      .subscribe(provinces => this.provinces = provinces);
  }

  communityForm = new FormGroup({
    name: new FormControl('', Validators.required),
    province: new FormControl('', Validators.required),
  });


  addCommunity() {
    this.communityService.addCommunity(this.communityForm.value)
      .subscribe(community => this.community = community);
    this._router.navigate([''])
  }
}
