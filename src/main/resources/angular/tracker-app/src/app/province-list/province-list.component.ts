import { Component, OnInit } from '@angular/core';
import {Province} from "../province";
import {ProvinceService} from "../province.service";

@Component({
  selector: 'app-province-list',
  templateUrl: './province-list.component.html',
  styleUrls: ['./province-list.component.scss']
})
export class ProvinceListComponent implements OnInit {
  provinces: Province[];
  constructor(private provinceService: ProvinceService) { }

  ngOnInit(): void {
    this.provinceService.getProvinces()
      .subscribe(provinces => this.provinces = provinces);
  }

}
