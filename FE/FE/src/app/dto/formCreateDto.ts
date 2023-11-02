import {ImgDto} from "./ImgDto";

export interface FormCreateDto {
  id?: number;
  name?: string;
  description?: string;
  images: ImgDto;
}
