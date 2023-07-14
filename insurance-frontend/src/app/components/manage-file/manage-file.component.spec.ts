import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageFileComponent } from './manage-file.component';

describe('ManageFileComponent', () => {
  let component: ManageFileComponent;
  let fixture: ComponentFixture<ManageFileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageFileComponent]
    });
    fixture = TestBed.createComponent(ManageFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
