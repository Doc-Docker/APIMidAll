import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionsFormComponent } from './promotions-form.component';

describe('PromotionsFormComponent', () => {
  let component: PromotionsFormComponent;
  let fixture: ComponentFixture<PromotionsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
