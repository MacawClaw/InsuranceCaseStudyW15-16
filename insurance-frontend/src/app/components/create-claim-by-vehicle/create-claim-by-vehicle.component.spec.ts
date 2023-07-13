import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateClaimByVehicleComponent } from './create-claim-by-vehicle.component';

describe('CreateClaimByVehicleComponent', () => {
  let component: CreateClaimByVehicleComponent;
  let fixture: ComponentFixture<CreateClaimByVehicleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateClaimByVehicleComponent]
    });
    fixture = TestBed.createComponent(CreateClaimByVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
