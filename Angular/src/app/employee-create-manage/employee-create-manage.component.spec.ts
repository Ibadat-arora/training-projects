import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeCreateManage } from './employee-create-manage.component';

describe('EmployeeCreateManage', () => {
  let component: EmployeeCreateManage;
  let fixture: ComponentFixture<EmployeeCreateManage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeCreateManage ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeCreateManage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
