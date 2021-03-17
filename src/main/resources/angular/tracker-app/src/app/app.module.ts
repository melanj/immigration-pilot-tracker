import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProvinceListComponent } from './province-list/province-list.component';
import { ProvinceDetailsComponent } from './province-details/province-details.component';
import { ProvinceAddComponent } from './province-add/province-add.component';
import { CommunityListComponent } from './community-list/community-list.component';
import { CommunityAddComponent } from './community-add/community-add.component';
import { CommunityDetailsComponent } from './community-details/community-details.component';
import { PilotListComponent } from './pilot-list/pilot-list.component';
import { PilotAddComponent } from './pilot-add/pilot-add.component';
import { PilotDetailsComponent } from './pilot-details/pilot-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    ProvinceListComponent,
    ProvinceDetailsComponent,
    ProvinceAddComponent,
    CommunityListComponent,
    CommunityAddComponent,
    CommunityDetailsComponent,
    PilotListComponent,
    PilotAddComponent,
    PilotDetailsComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '', component: ProvinceListComponent },
      { path: 'provinces/:provinceId', component: ProvinceDetailsComponent },
      { path: 'add-province', component: ProvinceAddComponent },
      { path: 'add-community', component: CommunityAddComponent }
    ], { enableTracing: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
