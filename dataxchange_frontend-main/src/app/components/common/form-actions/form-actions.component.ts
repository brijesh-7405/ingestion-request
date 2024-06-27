import { AfterViewInit, Component, OnInit } from '@angular/core';
import { PageHeaderService } from '../../../services/page-header.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-actions',
  templateUrl: './form-actions.component.html',
  styleUrl: './form-actions.component.scss',
})
export class FormActionsComponent implements AfterViewInit {
  private afterViewInited = false;
  public componentName?: string;
  public componentViewType: string = ""

  constructor(private pageHeaderService: PageHeaderService, private router: Router) {
    this.componentName = this.pageHeaderService?.component_name;
    if (this.componentName == 'DynamicDomainFormComponent') {
      this.componentViewType = this.pageHeaderService?.domainFormComponent['editData'] ? 'existing' : 'new';
    }
    else {
      this.componentViewType = this.pageHeaderService?.domainFormComponent['componetViewType'];
    }

  }
  ngAfterViewInit(): void {
    setTimeout(() => (this.afterViewInited = true));
  }

  hasPrevious(): boolean {
    if (!this.afterViewInited) {
      return false;
    }

    return this.pageHeaderService.domainFormComponent!.currentStep > 0;
  }

  hasNext(): boolean {
    if (!this.afterViewInited) {
      return false;
    }

    return (
      this.pageHeaderService.domainFormComponent!.currentStep + 1 <
      this.pageHeaderService.domainFormComponent!.totalSteps
    );
  }

  isLastStep(): boolean {
    if (!this.afterViewInited) {
      return false;
    }

    return (
      this.pageHeaderService.domainFormComponent!.currentStep + 1 ===
      this.pageHeaderService.domainFormComponent!.totalSteps
    );
  }

  previous(): void {
    this.pageHeaderService.domainFormComponent?.previous();
  }

  next(): void {
    this.pageHeaderService.domainFormComponent?.next();
  }

  submit(): void {
    this.pageHeaderService.domainFormComponent?.submit();
  }

  submitOnEditPage(): void {
    this.pageHeaderService.domainFormComponent?.submitOnEditPage();
  }

  save(): void {
    this.pageHeaderService.domainFormComponent?.save();
  }

  update(): void {
    this.pageHeaderService.domainFormComponent?.update();
  }

  saveAndClose(): void {
    this.pageHeaderService.domainFormComponent?.saveAndClose();
  }

  cancel(): void {
    this.pageHeaderService.domainFormComponent?.cancel();
  }
  isNewForm() {
    return this.componentViewType == 'new';
  }
  isReviewForm() {
    return this.componentViewType == 'review';
  }
  allowEdit(): boolean {
    const statusName = this.pageHeaderService.domainFormComponent?.viewRequesterData?.activeRequestStatus?.status?.statusName?.toLowerCase();
    return statusName?.includes('draft') || statusName?.includes('triage pending approval');
  }
  edit(): void {
    this.pageHeaderService.domainFormComponent?.edit();
  }

  sendToDataXchange(): void {
    this.pageHeaderService.domainFormComponent?.sendToDataXchange();
  }

  reject(): void {
    this.pageHeaderService.domainFormComponent?.reject();
  }

  moveToBacklog(): void {
    this.pageHeaderService.domainFormComponent?.moveToBacklog();
  }
  
  isDraft(): boolean {
    return  this.pageHeaderService.domainFormComponent?.editData?.activeRequestStatus?.status?.statusName?.toLowerCase() === "draft";
  }
}
