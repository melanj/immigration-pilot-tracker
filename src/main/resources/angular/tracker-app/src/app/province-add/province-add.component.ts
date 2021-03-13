import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {ProvinceService} from "../province.service";

@Component({
  selector: 'app-province-add',
  templateUrl: './province-add.component.html',
  styleUrls: ['./province-add.component.scss']
})
export class ProvinceAddComponent implements OnInit {
  province;
  constructor(private _router: Router,
              private provinceService: ProvinceService) {
  }

  provinceForm = new FormGroup({
    name: new FormControl('', Validators.required),
    capital: new FormControl('', Validators.required),
    postalAbbr: new FormControl('', Validators.required),
    isoCode: new FormControl('', Validators.required),
  });

  ngOnInit(): void {
  }

  addProvince() {
    this.provinceService.addProvince(this.provinceForm.value)
      .subscribe(province => this.province = province);
    this._router.navigate([''])
  }
}
