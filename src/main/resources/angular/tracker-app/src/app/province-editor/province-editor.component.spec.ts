import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvinceEditorComponent } from './province-editor.component';

describe('ProvinceEditorComponent', () => {
  let component: ProvinceEditorComponent;
  let fixture: ComponentFixture<ProvinceEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvinceEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvinceEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
