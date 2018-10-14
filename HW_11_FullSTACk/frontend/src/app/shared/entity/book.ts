import { Author } from './author';
import { Genre } from './genre';

export class Book {
  id: string;
  name: string;
  description: string;
  authors: Author[];
  genres: Genre[];
}
