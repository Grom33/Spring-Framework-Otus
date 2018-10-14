import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author } from '../../shared/entity/author';
import { AuthorService } from '../../shared/service/author.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-author-detail',
  templateUrl: './author-detail.component.html',
  styleUrls: ['./author-detail.component.css']
})
export class AuthorDetailComponent implements OnInit {
  public author: Author;
  private authorId: string;

  constructor(private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router,
    private _location: Location
  ) { }

  ngOnInit() {
    this.authorId = this.route.snapshot.paramMap.get('authorId');
    this.authorService.getAuthorById(this.authorId).subscribe(
      (data: Author) => {
        this.author = data;
      });
  }

  remove() {
    this.authorService.remove(this.authorId);
    this.backClicked();
  }

  backClicked() {
    this.router.navigate(['authors/']);
  }
}
