export class Product {
    code!: string;                    
    name!: string;                    
    presentation!: Presentation;          
    unitsPerPresentation!: number;   
    pricePerPresentation!: number;   
}

export class Presentation {
    id!: number;
    name!: string;
}

export interface ProductDTO {
  code: string;
  name: string;
  presentationId: number;
  unitsPerPresentation: number;
  pricePerPresentation: number;
}