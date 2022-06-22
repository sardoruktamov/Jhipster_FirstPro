export interface IStudent {
  id?: number;
  name?: string;
  lastName?: string | null;
  age?: number | null;
  birthOfDay?: Date;
  phoneNumber?: string;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public name?: string,
    public lastName?: string | null,
    public age?: number | null,
    public birthOfDay?: Date,
    public phoneNumber?: string
  ) {}
}