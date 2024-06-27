import { Component, OnInit, HostListener } from '@angular/core';
import { AppService } from '../../app.service';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { MatRadioChange } from '@angular/material/radio';

export type IngestionResponse = {
  totalAll: number;
  totalPendingApproval: number;
  totalCompletedRequest: number;
  totalRejected: number;
  items?: any[];
};

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.scss']
})
export class RequestListComponent implements OnInit {
  dropdownList = ["All Requests"];
  statusOptions = ['All', 'Pending', 'Completed', 'Rejected'];
  sortOptions = [
    'Request Id', 'Required For', 'Requested By',
    'Analysis Initiated Date', 'Analysis Completed Date',
    'Last Updated Date', 'Dataset SME', 'Requester Email', 'Status'
  ];
  selectedType = this.dropdownList[0];
  currentStatus = "All";
  currentTab = "Awaiting my Approval";
  orderByField = "modifiedDate";
  sortDirection = "desc";
  selectedStatus = "All";

  allRequest: IngestionResponse = {
    totalAll: 0,
    totalPendingApproval: 0,
    totalCompletedRequest: 0,
    totalRejected: 0,
    items: []
  };
  myPendings = 0;

  currentPage = 1;
  moreDataAvailable = true;

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    this.fetchRequests(true, false, 'All');
  }

  fetchRequests(my_approvals: boolean, my_submissions: boolean, status: string, page = 1, per_page = 20, orderBy = this.orderByField, orderDirection = this.sortDirection): void {
    this.appService.fetchAllMyRequests(my_approvals, my_submissions, page, per_page, status, orderBy, orderDirection).subscribe(res => {
      if (page === 1) {
        this.allRequest = res;
      } else {
        this.allRequest.items = [...(this.allRequest.items ?? []), ...(res.items ?? [])];
      }
      this.moreDataAvailable = (res.items?.length ?? 0) === per_page;
      if (my_approvals) {
        this.myPendings = res.totalAll;
      }
    });
  }
  

  onTabChange(event: MatTabChangeEvent): void {
    const isApprovalTab = event.index === 0;
    this.currentTab = isApprovalTab ? "Awaiting my Approval" : "My Submissions";
    this.currentStatus = this.selectedStatus = "All";
    this.currentPage = 1;
    this.fetchRequests(isApprovalTab, !isApprovalTab, 'All');
  }

  getCount(status: string): number {
    const statusCountMap: Record<string, number> = {
      'Pending': this.allRequest.totalPendingApproval,
      'Completed': this.allRequest.totalCompletedRequest,
      'Rejected': this.allRequest.totalRejected,
      'All': this.allRequest.totalAll
    };
    return statusCountMap[status] || this.allRequest.totalAll;
  }

  orderDirection(direction: string): void {
    this.sortDirection = direction;
    const isMySubmissions = this.currentTab === "My Submissions";
    this.currentPage = 1;
    this.fetchRequests(!isMySubmissions, isMySubmissions, this.currentStatus, 1, 20, this.orderByField, direction);
  }

  onStatusChange(event: MatRadioChange): void {
    const statusMapping: Record<string, string> = {
      'Pending': 'PendingApproval',
      'Completed': 'CompletedRequest',
      'Rejected': 'Rejected',
      'All': 'All'
    };
    this.currentStatus = statusMapping[event.value] || event.value;
    this.currentPage = 1;
    this.fetchRequests(false, true, this.currentStatus);
  }

  onSortOptionSelect(sortOption: string): void {
    const sortMapping: Record<string, string> = {
      'Request Id': 'ingestionRequestId',
      'Required For': 'datasetRequiredForRef',
      'Requested By': 'requestedByName',
      'Analysis Initiated Date': 'analysisInitDt',
      'Analysis Completed Date': 'analysisEndDt',
      'Last Updated Date': 'modifiedDate',
      'Dataset SME': 'datasetSmeName',
      'Requester Email': 'requesterByEmail',
      'Status': 'activeRequestStatus'
    };
    this.orderByField = sortMapping[sortOption] || 'modifiedDate';
    const isMySubmissions = this.currentTab === "My Submissions";
    this.currentPage = 1;
    this.fetchRequests(!isMySubmissions, isMySubmissions, this.currentStatus);
  }

  @HostListener('window:scroll', [])
  onWindowScroll(): void {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
      if (this.moreDataAvailable) {
        this.currentPage++;
        const isMySubmissions = this.currentTab === "My Submissions";
        this.fetchRequests(!isMySubmissions, isMySubmissions, this.currentStatus, this.currentPage);
      }
    }
  }

  onSelectType(event: any): void {
    throw new Error('Method not implemented.');
  }
}