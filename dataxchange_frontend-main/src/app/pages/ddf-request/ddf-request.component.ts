import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { ActivatedRoute } from '@angular/router';
import { IngestionRequestDetails } from '../../models/models';

@Component({
  selector: 'app-ddf-request',
  templateUrl: './ddf-request.component.html',
  styleUrl: './ddf-request.component.scss'
})
export class DdfRequestComponent implements OnInit {
  headerBreadcrumbs = [
    { label: 'Home', url: '/' },
    { label: '-' }
  ]
  public editRequestData: IngestionRequestDetails | undefined;

  constructor(private appService: AppService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const requestId = params.get('requestId');
      if(!requestId)
        return;
      this.appService.getRequestById(requestId).subscribe(res => {
        this.editRequestData = res;
      });
    })
  }
}
