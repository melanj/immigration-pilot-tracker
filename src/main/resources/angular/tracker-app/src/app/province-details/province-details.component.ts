import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ProvinceService} from "../province.service";

@Component({
  selector: 'app-province-editor',
  templateUrl: './province-details.component.html',
  styleUrls: ['./province-details.component.scss']
})
export class ProvinceDetailsComponent implements OnInit {
  province;

  constructor(private route: ActivatedRoute,
              private provinceService: ProvinceService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    const provinceId = Number(routeParams.get('provinceId'));
    this.provinceService.getProvince(provinceId)
      .subscribe(province => this.province = province);
  }

}
