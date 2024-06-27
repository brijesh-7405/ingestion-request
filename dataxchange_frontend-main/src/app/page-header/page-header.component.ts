import { Portal } from '@angular/cdk/portal';
import { Component, Input, OnDestroy } from '@angular/core';
import { PageHeaderService } from '../services/page-header.service';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map, mergeMap } from 'rxjs';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html',
  styleUrl: './page-header.component.scss'
})
export class PageHeaderComponent implements OnDestroy {
  public backLabel = '';
  @Input() backUrl = '';
  @Input() breadcrumbs: { label: string; url?: string; }[] = [];

  actionsPortal: Portal<any> | undefined;

  constructor(private pageHeaderService: PageHeaderService, private router: Router, private route: ActivatedRoute) {
    this.pageHeaderService.pageHeader = this;
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => this.route),
      map(route => {
        while (route.firstChild) route = route.firstChild;
        return route;
      }),
      filter(route => route.outlet === 'primary'),
      mergeMap(route => route.data)
    )
      .subscribe(data => {
        this.backLabel = data['title'];
      });
  }

  ngOnDestroy(): void {
    this.pageHeaderService.pageHeader = undefined
  }

  attachActions(portal: Portal<any>): void {
    this.actionsPortal = portal;
  }

  removeActions(): void {
    this.actionsPortal = undefined;
  }
}

