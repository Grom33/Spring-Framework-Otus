import {Component, Input} from '@angular/core';
import {Book} from '../../shared/entity/book';

@Component({
  selector: 'app-book-item',
  templateUrl: './book-item.component.html',
  styleUrls: ['./book-item.component.css']
})
export class BookItemComponent {

  @Input() book: Book;
}
