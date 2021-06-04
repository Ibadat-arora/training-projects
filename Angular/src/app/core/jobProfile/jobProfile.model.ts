import { Deserializable } from "../model/deserializable.model";

export class JobProfile implements Deserializable {
   id : number = 0 ;
   jobProfileName : string = "" ;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}