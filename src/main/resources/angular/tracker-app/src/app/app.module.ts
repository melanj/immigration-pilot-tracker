import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProvinceListComponent } from './province-list/province-list.component';
import { ProvinceDetailsComponent } from './province-details/province-details.component';
import { ProvinceAddComponent } from './province-add/province-add.component';

@NgModule({
  declarations: [
    AppComponent,
    ProvinceListComponent,
    ProvinceDetailsComponent,
    ProvinceAddComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '', component: ProvinceListComponent },
      { path: 'provinces/:provinceId', component: ProvinceDetailsComponent },
      { path: 'add-province', component: ProvinceAddComponent }
    ], { enableTracing: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
