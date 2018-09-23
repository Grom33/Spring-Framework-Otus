import { Component, Input} from '@angular/core';
import { Author } from '../../shared/entity/author';

@Component({
  selector: 'app-author-item',
  templateUrl: './author-item.component.html',
  styleUrls: ['./author-item.component.css']
})

export class AuthorItemComponent {
  @Input() author: Author;
}
