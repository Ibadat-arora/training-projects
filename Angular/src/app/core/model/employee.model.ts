import { Deserializable } from "./deserializable.model";

export class Employee implements Deserializable {
  id: number = 0;
  firstName: string = "";
  lastName: string = "";
  address: string = "";
  email: string = "";
  dateOfJoining: Date = new Date();
  jobProfile: string = "";

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}