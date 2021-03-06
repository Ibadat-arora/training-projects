import { Deserializable } from "./deserializable.model";

export class Employee implements Deserializable {
  id: number = 0;
  firstName: string = "";
  lastName: string = "";
  address: string = "";
  email: string = "";
  dateOfJoining: Date = new Date();
  jobProfileId: number = 0;
  userRoleId : number = 0 ;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}