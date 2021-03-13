import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ProvinceListComponent } from './province-list/province-list.component';
import { ProvinceDetailsComponent } from './province-details/province-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ProvinceListComponent,
    ProvinceDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', component: ProvinceListComponent },
      { path: 'provinces/:provinceId', component: ProvinceDetailsComponent }
    ], { enableTracing: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
