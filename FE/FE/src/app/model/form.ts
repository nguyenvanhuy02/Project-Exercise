import {Img} from './img';

export interface Form {
  id: number;
  name: string;
  description?: string;
  images: Img[];
}
