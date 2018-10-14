import { Component, Input } from '@angular/core';
import { Genre } from '../../shared/entity/genre';

@Component({
  selector: 'app-genre-item',
  templateUrl: './genre-item.component.html',
  styleUrls: ['./genre-item.component.css']
})

export class GenreItemComponent {
  @Input() genre: Genre;
}
