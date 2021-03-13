import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ProvinceListComponent } from './province-list/province-list.component';
import { ProvinceEditorComponent } from './province-editor/province-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    ProvinceListComponent,
    ProvinceEditorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', component: ProvinceListComponent },
      { path: 'provinces/:provinceId', component: ProvinceEditorComponent }
    ], { enableTracing: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
