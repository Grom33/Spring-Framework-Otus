import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author } from '../../shared/entity/author';
import { AuthorService } from '../../shared/service/author.service';

@Component({
  selector: 'app-authot-edit',
  templateUrl: './authot-edit.component.html',
  styleUrls: ['./authot-edit.component.css']
})
export class AuthotEditComponent implements OnInit {
  public author: Author;
  private authorId: string;

  constructor(private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.authorId = this.route.snapshot.paramMap.get('authorId');
    if (Number(this.authorId) === 0) {
      this.author = new Author();
    } else {
      this.authorService.getAuthorById(this.authorId).subscribe(
        (data: Author) => {
          this.author = data;
        });
    }
  }

  save() {
    this.authorService.saveAuthor(this.author);
    this.backClicked();
  }

  backClicked() {
    this.router.navigate(['authors/']);
  }
}
