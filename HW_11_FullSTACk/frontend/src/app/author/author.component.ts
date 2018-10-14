import { Component, OnInit } from '@angular/core';
import { Author } from '../shared/entity/author';
import { AuthorService } from '../shared/service/author.service';


@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  authors: Author[] = [];
  authorCount: number;

  constructor(private authorService: AuthorService) {
  }

  ngOnInit() {
    this.authorService.getAuthors().subscribe(
      (data: Author[]) => {
        console.log(data);
        this.authors = data;
        this.authorCount = this.authors.length;
      });
  }

}
