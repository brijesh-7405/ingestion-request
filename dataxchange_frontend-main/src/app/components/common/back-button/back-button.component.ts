import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, NavigationEnd } from '@angular/router';

interface BreadcrumbItem {
  label: string;
  url?: string;
}

const defaultBreadcrumbs: BreadcrumbItem[] = [
  {
    label: 'Home',
    url: '/'
  },
  {
    label: '-'
  }
];

@Component({
  selector: 'app-back-button',
  templateUrl: './back-button.component.html',
  styleUrl: './back-button.component.scss'
})
export class BackButtonComponent implements OnInit {
  @Input() label = 'Back';
  @Input() breadcrumbItems: BreadcrumbItem[] = defaultBreadcrumbs;

  constructor(public location: Location, private router: Router) { }
  
  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateBreadcrumbs(event.urlAfterRedirects);
      }
    });
  }

  updateBreadcrumbs(url: string) {
    if (url === '/manage-request') {
      this.breadcrumbItems = [
        {
          label: 'Home',
          url: '/'
        },
        {
          label: 'Manage Request',
        }
      ];
    } else {
      this.breadcrumbItems = defaultBreadcrumbs;
    }
  }

  goBack() {
    this.location.back();
  }
}
