import { TestBed } from '@angular/core/testing';

import { UserEmitterService } from './user-emitter.service';

describe('UserEmitterService', () => {
  let service: UserEmitterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserEmitterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
